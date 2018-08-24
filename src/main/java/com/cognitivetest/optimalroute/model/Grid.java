package com.cognitivetest.optimalroute.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by sumanth on 8/22/18.
 */
@Getter
@Setter
public class Grid {
  private Map<Character, LinkedList<Edge>> nodeMap;

  public Grid() {
    this.nodeMap = new HashMap<>();
  }

  public void addToGrid(Edge edge) {
    LinkedList<Edge> sourceList = nodeMap.get(edge.getU());
    LinkedList<Edge> destList = nodeMap.get(edge.getV());

    sourceList = (sourceList == null) ? new LinkedList<>() : sourceList;
    destList = (destList == null) ? new LinkedList<>() : destList;

    sourceList.add(edge);
    nodeMap.put(edge.getU(), sourceList);

    destList.add(new Edge(edge.getV(), edge.getU(), edge.getDistance(), edge.getSpeedRate()));
    nodeMap.put(edge.getV(), destList);
  }

  public void updateGrid(Edge edge) {
    for (Edge n : nodeMap.get(edge.getU())) {
      if (n != null && n.getV() == edge.getV()) {
        n.setDistance(edge.getDistance());
        n.setSpeedRate(edge.getSpeedRate());
      }
    }
  }

  public void deleteEdge(Edge edge) {
    // removing edges from u to v of adjacency list
    for (Edge n : nodeMap.get(edge.getU())) {
      if (n.getV() == edge.getV()) {
        nodeMap.get(edge.getU()).remove(n);
        if (nodeMap.get(edge.getU()).size() == 0) {
          nodeMap.remove(edge.getU());
        }
      }
    }
    // removing edges from v to u of adjacency list
    for (Edge n : nodeMap.get(edge.getV())) {
      if (n.getU() == edge.getU()) {
        nodeMap.get(edge.getV()).remove(n);
        if (nodeMap.get(edge.getU()).size() == 0) {
          nodeMap.remove(edge.getU());
        }
      }
    }
  }
}