package com.lsoftware.reactive.examples.msvreporter.services.impl;



import com.lsoftware.reactive.examples.msvreporter.entities.Suspicious;
import com.lsoftware.reactive.examples.msvreporter.repositories.SuspiciousRepository;
import com.lsoftware.reactive.examples.msvreporter.services.UpdaterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class SuspiciousMongoService implements UpdaterService<Suspicious, Suspicious> {
    @Autowired
    private SuspiciousRepository suspiciousRepository;

    @Override
    public Mono<Suspicious> update(Suspicious suspicious) {
        return suspiciousRepository.save(suspicious);
    }

}
