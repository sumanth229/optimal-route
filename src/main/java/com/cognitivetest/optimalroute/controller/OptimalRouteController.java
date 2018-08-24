package com.cognitivetest.optimalroute.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognitivetest.optimalroute.model.DisplayDTO;
import com.cognitivetest.optimalroute.model.OptimalPathDTO;
import com.cognitivetest.optimalroute.model.RequestModel;
import com.cognitivetest.optimalroute.response.RouteResponse;
import com.cognitivetest.optimalroute.service.OptimalRouteService;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by sumanth on 8/22/18.
 */
@Slf4j
@CrossOrigin
@EnableSwagger2
@RequestMapping("/v1")
@RestController
public class OptimalRouteController {

  @Autowired
  private OptimalRouteService optimalRouteService;

  /**
   * create grid/route
   *
   * @param requestModel
   * @return true/false status of creation
   */
  @PostMapping("/route/create")
  public ResponseEntity<RouteResponse<Boolean>> createRoute(
      @RequestBody RequestModel requestModel) {
    log.info("Requested to create route with model : {}", requestModel);
    RouteResponse<Boolean> response = optimalRouteService.createRoute(requestModel);
    return new ResponseEntity<>(response, response.getStatusCode());
  }

  /**
   * update grid/route
   *
   * @param requestModel
   * @return true/false status of update
   */
  @PutMapping("/route/update")
  public ResponseEntity<RouteResponse<Boolean>> updateRoute(
      @RequestBody RequestModel requestModel) {
    log.info("Requested to update route with model : {}", requestModel);
    RouteResponse<Boolean> response = optimalRouteService.updateRoute(requestModel);
    return new ResponseEntity<>(response, response.getStatusCode());
  }

  /**
   * get grid/route
   *
   * @return List of nodes with adjacent nodes
   */
  @GetMapping("/route/get")
  public ResponseEntity<RouteResponse<List<DisplayDTO>>> getRoute() {
    log.info("Requested to get the route/grid");
    RouteResponse<List<DisplayDTO>> response = optimalRouteService.getRoute();
    return new ResponseEntity<>(response, response.getStatusCode());
  }

  /**
   * get optimal route given source and destination
   *
   * @param source
   * @param destination
   * @return
   */
  @GetMapping("/route/optimalroute")
  public ResponseEntity<RouteResponse<OptimalPathDTO>> getOptimalRoute(
      @RequestParam("source") Character source,
      @RequestParam("destination") Character destination) {
    RouteResponse<OptimalPathDTO> response =
        optimalRouteService.getOptimalPath(source, destination);
    return new ResponseEntity<>(response, response.getStatusCode());
  }
}
