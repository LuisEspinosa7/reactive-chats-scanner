package com.lsoftware.reactive.examples.msvchatgenerator.services;

import reactor.core.publisher.Flux;

public interface QueryAllService<R> {
    Flux<R> getAll();
}
