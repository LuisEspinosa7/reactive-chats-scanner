package com.lsoftware.reactive.examples.msvreporter.repositories;


import com.lsoftware.reactive.examples.msvreporter.entities.ChatLine;
import com.lsoftware.reactive.examples.msvreporter.entities.Suspicious;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatLinesRepository extends ReactiveMongoRepository<ChatLine, String> {

}
