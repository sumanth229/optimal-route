package com.cognitivetest.optimalroute.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cognitivetest.optimalroute.enums.OperationType;
import com.cognitivetest.optimalroute.model.DisplayDTO;
import com.cognitivetest.optimalroute.model.Edge;
import com.cognitivetest.optimalroute.model.Grid;
import com.cognitivetest.optimalroute.model.OptimalPathDTO;
import com.cognitivetest.optimalroute.model.RequestModel;
import com.cognitivetest.optimalroute.response.RouteResponse;
import com.cognitivetest.optimalroute.service.OptimalRouteService;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by sumanth on 8/22/18.
 */
@Slf4j
@Service
public class OptimalRouteServiceImpl implements OptimalRouteService {

  private static Grid grid;

  @Override
  public RouteResponse<Boolean> createRoute(RequestModel requestModel) {
    log.info("Requested to create route with model : {}", requestModel);
    RouteResponse<Boolean> response = new RouteResponse<>();
    response.setResponseObject(Boolean.FALSE);
    if (requestModel != null) {
      if (CollectionUtils.isEmpty(requestModel.getEdges())) {
        log.error("Invalid data, there should be at least two nodes in the grid");
      }
      grid = new Grid();
      // add nodes to grid/route
      requestModel.getEdges().forEach(grid::addToGrid);
      response.setResponseObject(Boolean.TRUE);
    }
    return response;
  }

  @Override
  public RouteResponse<Boolean> updateRoute(RequestModel requestModel) {
    log.info("Requested to create route with model : {}", requestModel);
    RouteResponse<Boolean> response = new RouteResponse<>();
    response.setResponseObject(Boolean.FALSE);
    requestModel.getEdges().forEach(this::updateByCase);
    response.setResponseObject(Boolean.TRUE);
    return response;
  }

  /**
   * updating grid based on the operation type
   * ADD: to add a new node
   * UPDATE: modify the speedRate and distance
   * DELETE: delete the edge
   *
   * @param edge
   */
  private void updateByCase(Edge edge) {
    OperationType operationType = edge.getOperationType();
    switch (operationType) {
      case ADD:
        grid.addToGrid(edge);
        break;
      case UPDATE:
        grid.updateGrid(edge);
        break;
      case DELETE:
        grid.deleteEdge(edge);
    }
  }

  @Override
  public RouteResponse<List<DisplayDTO>> getRoute() {
    log.info("Requested to get created route/grid");
    RouteResponse<List<DisplayDTO>> response = new RouteResponse<>();
    if (grid.getNodeMap() != null) {
      List<DisplayDTO> displayDTOS = new ArrayList<>();
      // iterating through the map display the nodes along with adjacent nodes
      for (Map.Entry<Character, LinkedList<Edge>> e : grid.getNodeMap().entrySet()) {
        DisplayDTO displayDTO = new DisplayDTO();
        displayDTO.setNode(e.getKey());
        displayDTO.setAdjacentNodes(e.getValue());
        displayDTOS.add(displayDTO);
      }
      response.setResponseObject(displayDTOS);
    } else {
      log.error("Grid/Route not found, please create one before fetching");
    }
    return response;
  }

  @Override
  public RouteResponse<OptimalPathDTO> getOptimalPath(Character source, Character destination) {
    log.info("Requested to get optimal path between the nodes  {} and {}", source, destination);
    RouteResponse<OptimalPathDTO> response = new RouteResponse<>();
    // initialize a map to store the times, previous node and shortest path set for all nodes
    Map<Character, Double> timeMap = new HashMap<>();
    Map<Character, Character> prevMap = new HashMap<>();
    Map<Character, Boolean> sptSet = new HashMap<>();

    log.info("Initializing values of each map to default values");
    grid.getNodeMap().forEach((character, nodes)->{
      timeMap.put(character, Double.MAX_VALUE);
      prevMap.put(character, '0');
      sptSet.put(character, false);
    });
    // marking source with zero time.
    timeMap.put(source, 0d);
    for (int count = 0; count < grid.getNodeMap().size() - 1; count++) {
      Character minNode = findMinDistanceNode(timeMap, sptSet);
      sptSet.put(minNode, true);
      for (Edge n : grid.getNodeMap().get(minNode)) {
        if (!sptSet.get(n.getV()) && timeMap.get(minNode) != Integer.MAX_VALUE &&
            timeMap.get(minNode) + ((double) n.getDistance() / n.getSpeedRate()) <
                timeMap.get(n.getV())) {
          timeMap
              .put(n.getV(), timeMap.get(minNode) + ((double) n.getDistance() / n.getSpeedRate()));
          prevMap.put(n.getV(), n.getU());
        }
      }
      if (minNode == destination) {
        break;
      }
    }
    // constructing optimal path as a string builder
    StringBuilder sb = getOptimalPath(source, destination, prevMap);
    OptimalPathDTO optimalPathDTO = new OptimalPathDTO(sb.toString(), timeMap.get(destination));
    response.setResponseObject(optimalPathDTO);
    return response;
  }

  private StringBuilder getOptimalPath(Character source, Character destination,
      Map<Character, Character> prevMap) {
    log.info("Requested to create optimal path/route between source : {} and destination : {}",
        source, destination);
    StringBuilder sb = new StringBuilder();
    Character temp = destination;
    sb.append(" -> ").append(destination);
    while (prevMap.get(temp) != source) {
      Character prev = prevMap.get(temp);
      // retrieving optimal path from prev node
      sb.insert(0, prev).insert(0, " -> ");
      temp = prev;
    }
    sb.insert(0, source);
    return sb;
  }

  private Character findMinDistanceNode(Map<Character, Double> distanceMap,
      Map<Character, Boolean> sptSet) {
    log.info("Requested to get minimum distance node from nodes which are not in sptSet");
    char minNode = '0';
    Double min = Double.MAX_VALUE;
    for (Map.Entry<Character, Double> e : distanceMap.entrySet()) {
      if (!sptSet.get(e.getKey()) && e.getValue() < min) {
        min = e.getValue();
        minNode = e.getKey();
      }
    }
    return minNode;
  }

}
