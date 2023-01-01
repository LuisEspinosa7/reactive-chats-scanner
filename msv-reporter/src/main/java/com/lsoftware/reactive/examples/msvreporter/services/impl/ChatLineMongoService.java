package com.lsoftware.reactive.examples.msvreporter.services.impl;


import com.lsoftware.reactive.examples.msvreporter.entities.ChatLine;
import com.lsoftware.reactive.examples.msvreporter.repositories.ChatLinesRepository;
import com.lsoftware.reactive.examples.msvreporter.services.QueryIndividualService;
import com.lsoftware.reactive.examples.msvreporter.services.UpdaterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ChatLineMongoService implements UpdaterService<ChatLine, ChatLine>,
        QueryIndividualService<ChatLine, String> {
    @Autowired
    private ChatLinesRepository chatLinesRepository;

    @Override
    public Mono<ChatLine> update(ChatLine chatLine) {
        return chatLinesRepository.save(chatLine);
    }

    @Override
    public Mono<ChatLine> findById(String id) {
        return chatLinesRepository.findById(id);
    }

}
