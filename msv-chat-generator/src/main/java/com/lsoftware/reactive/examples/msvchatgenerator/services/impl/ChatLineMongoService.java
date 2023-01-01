package com.lsoftware.reactive.examples.msvchatgenerator.services.impl;

import com.lsoftware.reactive.examples.msvchatgenerator.entities.ChatLine;
import com.lsoftware.reactive.examples.msvchatgenerator.repositories.ChatLinesRepository;
import com.lsoftware.reactive.examples.msvchatgenerator.services.InserterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ChatLineMongoService implements InserterService<ChatLine, ChatLine> {
    @Autowired
    private ChatLinesRepository repository;

    @Override
    public Mono<ChatLine> save(ChatLine chatLine) {
        //Additional business logic here
        return repository.save(chatLine);
    }
}
