package com.cognitivetest.optimalroute.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognitivetest.optimalroute.model.DisplayDTO;
import com.cognitivetest.optimalroute.model.RequestModel;
import com.cognitivetest.optimalroute.response.RouteResponse;
import com.cognitivetest.optimalroute.service.OptimalRouteService;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by sumanth on 8/22/18.
 */
@Slf4j
@RestController
@RequestMapping("/v1")
public class OptimalRouteController {

  @Autowired
  private OptimalRouteService optimalRouteService;

  @PostMapping("/route/create")
  public ResponseEntity<RouteResponse<Boolean>> createRoute(
      @RequestBody RequestModel requestModel) {
    log.info("Requested to create route with model : {}", requestModel);
    RouteResponse<Boolean> response = optimalRouteService.createRoute(requestModel);
    return new ResponseEntity<>(response, response.getStatusCode());
  }

  @PutMapping("/route/update")
  public RouteResponse<Boolean> updateRoute() {
    log.info("Requested to update route with model : {}");
    return null;
  }

  @GetMapping("/route/get")
  public ResponseEntity<RouteResponse<List<DisplayDTO>>> getRoute() {
    log.info("Requested to get the route/grid");
    RouteResponse<List<DisplayDTO>> response = optimalRouteService.getRoute();
    return new ResponseEntity<>(response, response.getStatusCode());
  }

  @GetMapping("/route/optimalroute")
  public RouteResponse<Boolean> getOptimalRoute() {
    return null;
  }
}
