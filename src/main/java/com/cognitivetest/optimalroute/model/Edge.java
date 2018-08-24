package com.cognitivetest.optimalroute.model;

import java.io.Serializable;

import com.cognitivetest.optimalroute.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by sumanth on 8/22/18.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Edge implements Serializable {
  Character u;
  Character v;
  Integer distance;
  Integer speedRate;
  OperationType operationType;

  public Edge(Character u, Character v, Integer distance, Integer speedRate) {
    this.u = u;
    this.v = v;
    this.distance = distance;
    this.speedRate = speedRate;
  }
}