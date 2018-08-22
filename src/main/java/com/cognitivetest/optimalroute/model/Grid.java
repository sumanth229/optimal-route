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
  private Map<Character, LinkedList<Node>> nodeMap;

  public Grid() {
    this.nodeMap = new HashMap<>();
  }

  public void addToGrid(Node node) {
    LinkedList<Node> sourceList = nodeMap.get(node.getU());
    LinkedList<Node> destList = nodeMap.get(node.getV());

    sourceList = (sourceList == null) ? new LinkedList<>() : sourceList;
    destList = (destList == null) ? new LinkedList<>() : destList;

    sourceList.add(node);
    nodeMap.put(node.getU(), sourceList);

    destList.add(new Node(node.getV(), node.getU(), node.getDistance(), node.getSpeedRate()));
    nodeMap.put(node.getV(), destList);
  }
}