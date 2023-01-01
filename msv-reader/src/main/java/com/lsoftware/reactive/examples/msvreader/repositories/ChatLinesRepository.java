package com.lsoftware.reactive.examples.msvreader.repositories;

import com.lsoftware.reactive.examples.msvreader.entities.ChatLine;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface ChatLinesRepository extends ReactiveMongoRepository<ChatLine, String> {
    Flux<ChatLine> findByAvailableTrueAndCheckedFalse();

}
