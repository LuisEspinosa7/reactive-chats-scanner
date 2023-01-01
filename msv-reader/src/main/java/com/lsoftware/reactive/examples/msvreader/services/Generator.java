package com.lsoftware.reactive.examples.msvreader.services;

import reactor.core.publisher.Flux;

public interface Generator<T> {
    Flux<T> getGenerator();
}
