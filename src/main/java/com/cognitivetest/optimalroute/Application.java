package com.cognitivetest.optimalroute;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.cognitivetest.optimalroute.model.Edge;
import com.cognitivetest.optimalroute.model.RequestModel;
import com.cognitivetest.optimalroute.service.OptimalRouteService;
import com.cognitivetest.optimalroute.service.impl.OptimalRouteServiceImpl;
import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnablePrometheusEndpoint
@EnableSwagger2
@ComponentScan(basePackages = {"com.cognitivetest.optimalroute"})
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
    OptimalRouteService optimalRouteService = new OptimalRouteServiceImpl();
    List<Edge> edges = new ArrayList<>();
    edges.add(new Edge('a', 'b', 1, 2));
    edges.add(new Edge('a', 'c', 2, 10));
    edges.add(new Edge('b', 'd', 3, 1));
    edges.add(new Edge('c', 'f', 4, 1));
    edges.add(new Edge('d', 'e', 5, 1));
    edges.add(new Edge('d', 'f', 7, 1));
    edges.add(new Edge('e', 'f', 6, 1));
    RequestModel requestModel = new RequestModel();
    requestModel.setEdges(edges);
    optimalRouteService.createRoute(requestModel);
  }

  @Bean
  public Docket gridApi() {
    return new Docket(DocumentationType.SWAGGER_2).select()
        .apis(RequestHandlerSelectors.basePackage("com.cognitivetest.optimalroute.controller"))
        .build().apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("Grid APIs").description("Grid APIs")
        .contact(new Contact("Vangapally Sumanth", "", "vangapallysumanth@gmail.com")).version("v1")
        .build();
  }
}
