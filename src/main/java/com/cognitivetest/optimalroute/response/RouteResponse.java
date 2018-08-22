package com.cognitivetest.optimalroute.response;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by sumanth on 8/22/18.
 */
@Getter
@Setter
@ToString(doNotUseGetters = true)
public class RouteResponse<T> implements Serializable {
  private static final long serialVersionUID = 5988199687608671318L;
  private T responseObject;

  @JsonIgnore
  private HttpStatus statusCode;

  public RouteResponse() {
    this.statusCode = HttpStatus.OK;
  }

  public RouteResponse(T responseObject) {
    this();
    this.responseObject = responseObject;
  }
}
