package com.lsoftware.reactive.examples.msvchatgenerator.services.impl;

import com.lsoftware.reactive.examples.msvchatgenerator.entities.ChatLine;
import com.lsoftware.reactive.examples.msvchatgenerator.providers.SequentialProvider;
import com.lsoftware.reactive.examples.msvchatgenerator.services.QueryAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Optional;
import java.util.Scanner;

@Component
public class ChatLineFileService implements QueryAllService<ChatLine> {

    @Value("${app.input.reactive.chatlines.interval}")
    private String reactiveInterval;

    @Autowired
    private SequentialProvider<ChatLine, Scanner> chatLineProvider;

    @Override
    public Flux<ChatLine> getAll() {
        return Flux.<ChatLine>generate(chatLineSynchronousSink -> {
            Optional<ChatLine> chatLine = chatLineProvider.getNext();
            if (chatLine.isPresent()) {
                chatLineSynchronousSink.next(chatLine.get());
            } else {
                chatLineSynchronousSink.complete();
            }
        }).delayElements(Duration.ofMillis(Long.valueOf(reactiveInterval)));
    }
}
