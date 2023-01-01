package com.lsoftware.reactive.examples.msvanalyzer.services;

import reactor.core.publisher.Mono;

public interface AnalyzerService<T> {
    Mono<T> analyze(T t);
}
