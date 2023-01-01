package com.lsoftware.reactive.examples.msvanalyzer.config;

import com.lsoftware.reactive.examples.msvanalyzer.handlers.AnalyzerHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration
public class RouterFunctionConfig {
    @Bean
    public RouterFunction<ServerResponse> routes(AnalyzerHandler handler){
        return route(POST("/api/v2/chatlines"), handler::analyze);
    }
}
