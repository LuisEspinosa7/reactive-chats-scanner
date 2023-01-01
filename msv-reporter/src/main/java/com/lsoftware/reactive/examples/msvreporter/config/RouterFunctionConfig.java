package com.lsoftware.reactive.examples.msvreporter.config;

import com.lsoftware.reactive.examples.msvreporter.handlers.ReporterHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterFunctionConfig {
    @Bean
    public RouterFunction<ServerResponse> routes(ReporterHandler handler){
        return route(POST("/api/v2/suspicious"), handler::suspicious)
                .and(route(POST("/api/v2/update"), handler::update));
    }
}
