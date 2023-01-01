package com.lsoftware.reactive.examples.msvreader.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientsConfig {
    @Value("${clients.analyzers.url}")
    private String analyzerUrl;


    @Bean
    public WebClient analyzerWebClient() {
        return WebClient.create(analyzerUrl);
    }
}
