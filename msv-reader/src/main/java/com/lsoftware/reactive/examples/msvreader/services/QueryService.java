package com.lsoftware.reactive.examples.msvreader.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface QueryService<R> {
    Flux<R> getAll();
}
