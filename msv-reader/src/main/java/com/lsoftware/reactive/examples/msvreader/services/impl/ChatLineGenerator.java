package com.lsoftware.reactive.examples.msvreader.services.impl;

import com.lsoftware.reactive.examples.msvreader.entities.ChatLine;
import com.lsoftware.reactive.examples.msvreader.services.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class ChatLineGenerator implements Generator<ChatLine> {

    @Autowired
    private ChatLineMongoService mongoService;

    @Override
    public Flux<ChatLine> getGenerator() {
        return null;
    }
}
