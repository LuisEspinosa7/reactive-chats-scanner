package com.lsoftware.reactive.examples.msvanalyzer.analyzers.impl;


import com.lsoftware.reactive.examples.msvanalyzer.analyzers.Analyzer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@Slf4j
public class ChatLineAnalyzer implements Analyzer<String, Boolean> {
    @Autowired
    private List<String> forbiddenWords;

    @Override
    public Boolean analyze(String chatLine) {
        for (String target : forbiddenWords) {
            if (chatLine.contains(target)){
                return true;
            }
        }
        return false;
    }
}
