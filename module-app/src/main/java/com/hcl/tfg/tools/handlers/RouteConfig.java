package com.hcl.tfg.tools.handlers;

import com.hcl.tfg.tools.handlers.ConfigValuesHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * Created by begin.samuel on 19-02-2018.
 */
@Configuration
public class RouteConfig {


    private ConfigValuesHandler configValuesHandler;
    public RouteConfig(ConfigValuesHandler configValuesHandler) {
        this.configValuesHandler = configValuesHandler;
    }

    @Bean
    public  RouterFunction<ServerResponse> routes() {
        return route(GET("/config-values").and(accept(MediaType.APPLICATION_JSON)), configValuesHandler::all)
                .andRoute(POST("/config-values").and(accept(MediaType.APPLICATION_JSON)), configValuesHandler::create)
                .andRoute(GET("/config-values/{id}").and(accept(MediaType.APPLICATION_JSON)), configValuesHandler::get)
                .andRoute(PUT("/config-values/{id}").and(accept(MediaType.APPLICATION_JSON)), configValuesHandler::update)
                .andRoute(DELETE("/config-values/{id}").and(accept(MediaType.APPLICATION_JSON)), configValuesHandler::delete);
    }
}
