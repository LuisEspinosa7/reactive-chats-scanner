package com.lsoftware.reactive.examples.msvchatgenerator.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@Configuration
public class MongoConfig {

//    private static final String CONNECTION_STRING = "mongodb://%s:%d";
//    private static final String MONGO_DB_URL = "mongodb://%s:%d";
//    private static final String MONGO_DB_NAME = "embeded_db";
//    private MongodExecutable mongodExecutable;
//
//    @Bean
//    @Primary
//    public ReactiveMongoTemplate mongoTemplate1() throws IOException {
//        String ip = "localhost";
//        int port = 27017;
//
//        ImmutableMongodConfig mongodConfig = MongodConfig
//                .builder()
//                .version(Version.Main.PRODUCTION)
//                .net(new Net(ip, port, Network.localhostIsIPv6()))
//                .build();
//
//        MongodStarter starter = MongodStarter.getDefaultInstance();
//        mongodExecutable = starter.prepare(mongodConfig);
//        mongodExecutable.start();
//        return new ReactiveMongoTemplate(MongoClients.create(String.format(CONNECTION_STRING, ip, port)), "test");
//    }

}
