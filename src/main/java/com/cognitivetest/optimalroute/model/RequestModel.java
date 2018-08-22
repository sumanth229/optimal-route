package com.cognitivetest.optimalroute.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by sumanth on 8/22/18.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestModel {

  Integer vertices;
  List<Node> nodes;
}
