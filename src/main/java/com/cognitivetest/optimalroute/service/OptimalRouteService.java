package com.cognitivetest.optimalroute.service;

import java.util.List;

import com.cognitivetest.optimalroute.model.DisplayDTO;
import com.cognitivetest.optimalroute.model.OptimalPathDTO;
import com.cognitivetest.optimalroute.model.RequestModel;
import com.cognitivetest.optimalroute.response.RouteResponse;

/**
 * Created by sumanth on 8/22/18.
 */
public interface OptimalRouteService {

  /**
   * create route
   *
   * @param requestModel request dto
   * @return creation status in boolean
   */
  RouteResponse<Boolean> createRoute(RequestModel requestModel);

  /**
   * update route
   *
   * @param requestModel update route
   * @return update status
   */
  RouteResponse<Boolean> updateRoute(RequestModel requestModel);

  /**
   * get grid/route
   *
   * @return list of nodes containing adjacent nodes
   */
  RouteResponse<List<DisplayDTO>> getRoute();

  /**
   * get optimal points given source and destination
   *
   * @param source
   * @param destination
   * @return
   */
  RouteResponse<OptimalPathDTO> getOptimalPath(Character source, Character destination);

}
