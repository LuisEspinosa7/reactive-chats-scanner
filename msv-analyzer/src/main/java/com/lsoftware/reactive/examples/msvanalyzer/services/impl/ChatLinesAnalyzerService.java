package com.lsoftware.reactive.examples.msvanalyzer.services.impl;

import com.lsoftware.reactive.examples.msvanalyzer.analyzers.Analyzer;
import com.lsoftware.reactive.examples.msvanalyzer.api.ApiResponse;
import com.lsoftware.reactive.examples.msvanalyzer.exception.TechnicalException;
import com.lsoftware.reactive.examples.msvanalyzer.model.ChatLineEvent;
import com.lsoftware.reactive.examples.msvanalyzer.services.AnalyzerService;
import com.lsoftware.reactive.examples.msvanalyzer.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class ChatLinesAnalyzerService implements AnalyzerService<ChatLineEvent> {
    @Autowired
    private Analyzer<String, Boolean> chatLineAnalyzer;

    @Autowired
    private WebClient reporterWebClient;

    @Override
    public Mono<ChatLineEvent> analyze(ChatLineEvent chatLineEvent) {
        boolean isSuspicious = chatLineAnalyzer.analyze(chatLineEvent.getMessage());
        chatLineEvent.setChecked(true);
        chatLineEvent.setAvailable(false);

        try {
            if (isSuspicious) {
                chatLineEvent.setSuspicious(true);
            }

            return reporterWebClient.post()
                    .uri("/update")
                    .body(Mono.just(chatLineEvent), ChatLineEvent.class)
                    .retrieve()
                    .bodyToMono(ApiResponse.class)
                    .flatMap(event -> {
                        if(!isSuspicious) {
                            log.info(Constants.NON_SUSPICIOUS_MESSAGE_SENT_TO_REPORTER);
                            return Mono.just(chatLineEvent);
                        }
                        return reporterWebClient.post()
                                .uri("/suspicious")
                                .body(Mono.just(chatLineEvent), ChatLineEvent.class)
                                .retrieve()
                                .bodyToMono(ApiResponse.class)
                                .flatMap(event2 -> {
                                    log.info(Constants.NON_SUSPICIOUS_MESSAGE_SENT_TO_REPORTER);
                                    return Mono.just(chatLineEvent);
                                });
                    })
                    .onErrorResume(error -> {
                        WebClientRequestException errorResponse = (WebClientRequestException) error;
                        log.error(errorResponse.toString());
                        return Mono.error(errorResponse);
                    });
        } catch (Exception e) {
            log.error(Constants.MESSAGING_ERROR_LOG_SENDING_UPDATER_MESSAGE, e);
            return Mono.error(new TechnicalException(Constants.MESSAGING_ERROR_SENDING_UPDATER_MESSAGE,
                    HttpStatus.INTERNAL_SERVER_ERROR));
        }

    }
}
