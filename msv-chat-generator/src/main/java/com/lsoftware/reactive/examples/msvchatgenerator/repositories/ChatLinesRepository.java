package com.lsoftware.reactive.examples.msvchatgenerator.repositories;

import com.lsoftware.reactive.examples.msvchatgenerator.entities.ChatLine;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatLinesRepository extends ReactiveMongoRepository<ChatLine, String> {

}
