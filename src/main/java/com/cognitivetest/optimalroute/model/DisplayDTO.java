package com.cognitivetest.optimalroute.model;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by sumanth on 8/22/18.
 */
@Getter
@Setter
@ToString
public class DisplayDTO implements Serializable{
  private Character node;
  private List<Edge> adjacentNodes;
}
