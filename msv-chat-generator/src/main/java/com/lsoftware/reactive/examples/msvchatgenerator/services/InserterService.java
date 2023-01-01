package com.lsoftware.reactive.examples.msvchatgenerator.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface InserterService<T, R> {
    Mono<R> save(T t);
}
