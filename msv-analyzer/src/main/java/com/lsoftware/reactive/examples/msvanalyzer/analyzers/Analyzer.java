package com.lsoftware.reactive.examples.msvanalyzer.analyzers;

public interface Analyzer<T, R> {
    R analyze(T t);
}
