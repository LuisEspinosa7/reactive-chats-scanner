package com.lsoftware.reactive.examples.msvreader;

import com.lsoftware.reactive.examples.msvreader.api.ApiResponse;
import com.lsoftware.reactive.examples.msvreader.entities.ChatLine;
import com.lsoftware.reactive.examples.msvreader.services.impl.ChatLineMongoService;
import com.lsoftware.reactive.examples.msvreader.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Mono;

import java.time.Duration;

@EnableRetry
@SpringBootApplication
@Slf4j
public class MsvReaderApplication implements CommandLineRunner {

    @Value("${app.db.reactive.chatlines.interval}")
    String dbInterval;

    @Value("${app.db.reactive.chatlines.limit}")
    String dbLimit;

    @Autowired
    private ChatLineMongoService mongoService;

    @Autowired
    private WebClient analyzerWebClient;

    public static void main(String[] args) {
        SpringApplication.run(MsvReaderApplication.class, args);
    }

    @Override
    public void run(String... args) {
        log.info(Constants.APP_STARTING);

            mongoService.getAll()
                    .limitRate(Integer.valueOf(dbLimit))
                    .delayElements(Duration.ofMillis(Long.valueOf(dbInterval)))
                    .flatMap(newLine -> analyzerWebClient.post()
                            .body(Mono.just(newLine), ChatLine.class)
                            .retrieve()
                            .bodyToMono(ApiResponse.class))
                    .flatMap(apiResponse -> mongoService.findById(apiResponse.getBody().getId())
                            .flatMap(line -> {
                                line.setAvailable(false);
                                return mongoService.update(line);
                            }))
                    .onErrorResume(error -> {
                        WebClientRequestException errorResponse = (WebClientRequestException) error;
                        log.error(errorResponse.toString());
                        return Mono.error(errorResponse);
                    })
                    .subscribe(chatLine -> log.info("ChatLine: " + chatLine.toString()));
    }
}
