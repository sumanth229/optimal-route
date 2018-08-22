package com.cognitivetest.optimalroute.service;

import java.util.List;

import com.cognitivetest.optimalroute.model.DisplayDTO;
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
   * @param requestModel
   * @return
   */
  RouteResponse<Boolean> updateRoute(RequestModel requestModel);

  /**
   * @return
   */
  RouteResponse<List<DisplayDTO>> getRoute();

}
