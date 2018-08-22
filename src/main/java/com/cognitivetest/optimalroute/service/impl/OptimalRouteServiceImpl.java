package com.cognitivetest.optimalroute.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cognitivetest.optimalroute.model.DisplayDTO;
import com.cognitivetest.optimalroute.model.Grid;
import com.cognitivetest.optimalroute.model.Node;
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
      grid = new Grid();
      // add nodes to grid/route
      requestModel.getNodes().forEach(grid::addToGrid);
      response.setResponseObject(Boolean.TRUE);
    }
    return response;
  }

  @Override
  public RouteResponse<Boolean> updateRoute(RequestModel requestModel) {
    return null;
  }

  @Override
  public RouteResponse<List<DisplayDTO>> getRoute() {
    log.info("Requested to get route/grid");
    RouteResponse<List<DisplayDTO>> response = new RouteResponse<>();
    List<DisplayDTO> displayDTOS = new ArrayList<>();
    // iterating through the map display the nodes along with adjacent nodes
    for (Map.Entry<Character, LinkedList<Node>> e : grid.getNodeMap().entrySet()) {
      DisplayDTO displayDTO = new DisplayDTO();
      displayDTO.setNode(e.getKey());
      displayDTO.setAdjacentNodes(e.getValue());
      displayDTOS.add(displayDTO);
    }
    response.setResponseObject(displayDTOS);
    return response;
  }
}
