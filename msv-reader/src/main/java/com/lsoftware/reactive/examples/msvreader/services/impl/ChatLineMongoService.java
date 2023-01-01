package com.lsoftware.reactive.examples.msvreader.services.impl;

import com.lsoftware.reactive.examples.msvreader.entities.ChatLine;
import com.lsoftware.reactive.examples.msvreader.repositories.ChatLinesRepository;
import com.lsoftware.reactive.examples.msvreader.services.QueryIndividualService;
import com.lsoftware.reactive.examples.msvreader.services.QueryService;
import com.lsoftware.reactive.examples.msvreader.services.UpdaterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ChatLineMongoService implements QueryService<ChatLine>, UpdaterService<ChatLine, ChatLine>,
        QueryIndividualService<ChatLine, String> {
    @Autowired
    private ChatLinesRepository chatLinesRepository;

    @Override
    public Flux<ChatLine> getAll() {
        return chatLinesRepository.findByAvailableTrueAndCheckedFalse();
    }

    @Override
    public Mono<ChatLine> update(ChatLine chatLine) {
        return chatLinesRepository.save(chatLine);
    }

    @Override
    public Mono<ChatLine> findById(String id) {
        return chatLinesRepository.findById(id);
    }
}
