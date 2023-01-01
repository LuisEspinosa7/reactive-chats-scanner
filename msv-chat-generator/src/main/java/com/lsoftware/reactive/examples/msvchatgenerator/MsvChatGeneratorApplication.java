package com.lsoftware.reactive.examples.msvchatgenerator;

import com.lsoftware.reactive.examples.msvchatgenerator.entities.ChatLine;
import com.lsoftware.reactive.examples.msvchatgenerator.providers.SequentialProvider;
import com.lsoftware.reactive.examples.msvchatgenerator.services.InserterService;
import com.lsoftware.reactive.examples.msvchatgenerator.services.QueryAllService;
import com.lsoftware.reactive.examples.msvchatgenerator.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;

import java.util.Scanner;

@SpringBootApplication
@Slf4j
public class MsvChatGeneratorApplication implements CommandLineRunner {
    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    @Autowired
    private QueryAllService<ChatLine> chatLineService;

    @Autowired
    private InserterService<ChatLine, ChatLine> chatLineMongoService;

    public static void main(String[] args) {
        SpringApplication.run(MsvChatGeneratorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info(Constants.APP_STARTING);
        mongoTemplate.dropCollection(Constants.CHATS_COLLECTION_NAME).subscribe();

        Flux<ChatLine> chatLineFlux = chatLineService.getAll()
                .flatMap(chatLineMongoService::save);

        chatLineFlux.subscribe(chatLine -> log.info(new StringBuilder()
                .append("Record sent [Chat Id: ").append(chatLine.getChatId())
                .append(", Chat Line: ").append(chatLine.getMessage()).toString()));
    }
}
