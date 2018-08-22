package com.cognitivetest.optimalroute;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.cognitivetest.optimalroute.model.Node;
import com.cognitivetest.optimalroute.model.RequestModel;
import com.cognitivetest.optimalroute.service.OptimalRouteService;
import com.cognitivetest.optimalroute.service.impl.OptimalRouteServiceImpl;

@SpringBootApplication
@ComponentScan(basePackages = {"com.cognitivetest.optimalroute"})
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
    OptimalRouteService optimalRouteService = new OptimalRouteServiceImpl();
    List<Node> nodes = new ArrayList<>();
    nodes.add(new Node('a', 'b', 1, 2));
    nodes.add(new Node('a', 'c', 1, 10));
    nodes.add(new Node('b', 'c', 5, 1));
    RequestModel requestModel = new RequestModel();
    requestModel.setNodes(nodes);
    optimalRouteService.createRoute(requestModel);
    optimalRouteService.getRoute();
  }
}
