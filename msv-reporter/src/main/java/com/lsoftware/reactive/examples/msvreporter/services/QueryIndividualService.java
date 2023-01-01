package com.lsoftware.reactive.examples.msvreporter.services;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import reactor.core.publisher.Mono;

public interface QueryIndividualService<R, T> {
    @Retryable(value = Exception.class, maxAttemptsExpression = "${app.db.retries.attempts}",
            backoff = @Backoff(delayExpression = "${app.db.retries.interval}"))
    Mono<R> findById(T t);
}
