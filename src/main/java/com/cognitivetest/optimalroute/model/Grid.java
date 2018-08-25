package com.cognitivetest.optimalroute.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by sumanth on 8/22/18.
 */
@Getter
@Setter
@Slf4j
public class Grid {
  private Map<Character, LinkedList<Edge>> nodeMap;

  public Grid() {
    this.nodeMap = new HashMap<>();
  }

  public void addToGrid(Edge edge) {
    log.info("Requested to add edge : {} to the grid", edge);
    // adjacency list for source and destination
    LinkedList<Edge> sourceList = nodeMap.get(edge.getU());
    LinkedList<Edge> destList = nodeMap.get(edge.getV());

    //checks for null and create new list
    sourceList = (sourceList == null) ? new LinkedList<>() : sourceList;
    destList = (destList == null) ? new LinkedList<>() : destList;

    sourceList.add(edge);
    nodeMap.put(edge.getU(), sourceList);

    destList.add(new Edge(edge.getV(), edge.getU(), edge.getDistance(), edge.getSpeedRate()));
    nodeMap.put(edge.getV(), destList);
  }

  public void updateGrid(Edge edge) {
    log.info("Requested to update edge : {} of the grid", edge);
    for (Edge n : nodeMap.get(edge.getU())) {
      if (n != null && n.getV().equals(edge.getV())) {
        n.setDistance(edge.getDistance());
        n.setSpeedRate(edge.getSpeedRate());
        break;
      }
    }
    for (Edge n : nodeMap.get(edge.getV())) {
      if (n != null && n.getV().equals(edge.getU())) {
        n.setDistance(edge.getDistance());
        n.setSpeedRate(edge.getSpeedRate());
        break;
      }
    }
  }

  public void deleteEdge(Edge edge) {
    log.info("Requested to delete edge : {}", edge);
    // removing edges from u to v of adjacency list
    for (Edge n : nodeMap.get(edge.getU())) {
      if (n.getV().equals(edge.getV())) {
        nodeMap.get(edge.getU()).remove(n);
        if (nodeMap.get(edge.getU()).size() == 0) {
          nodeMap.remove(edge.getU());
        }
        break;
      }
    }
    // removing edges from v to u of adjacency list
    for (Edge n : nodeMap.get(edge.getV())) {
      if (n.getV().equals(edge.getU())) {
        nodeMap.get(edge.getV()).remove(n);
        if (nodeMap.get(edge.getV()).size() == 0) {
          nodeMap.remove(edge.getV());
        }
        break;
      }
    }
  }
}