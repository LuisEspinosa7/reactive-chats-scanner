package com.lsoftware.reactive.examples.msvchatgenerator.providers;

import java.util.Optional;

public abstract class SequentialProvider<T, S> {
    protected S source;

    public SequentialProvider(S source){
        this.source = source;
    }

    protected abstract Optional<T> processNext();

    public Optional<T> getNext(){
        return processNext();
    }
}
