package com.lsoftware.reactive.examples.msvanalyzer.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientsConfig {

    @Value("${clients.reporter.url}")
    private String reporterUrl;


    @Bean
    public WebClient reporterWebClient() {
        return WebClient.create(reporterUrl);
    }
}
