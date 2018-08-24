package com.cognitivetest.optimalroute.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by sumanth on 8/24/18.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OptimalPathDTO implements Serializable{
  private String optimalPath;
  private Double optimalTime;
}
