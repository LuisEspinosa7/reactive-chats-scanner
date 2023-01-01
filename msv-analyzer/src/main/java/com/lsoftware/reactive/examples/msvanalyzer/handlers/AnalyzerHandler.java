package com.lsoftware.reactive.examples.msvanalyzer.handlers;

import com.lsoftware.reactive.examples.msvanalyzer.api.ApiResponse;
import com.lsoftware.reactive.examples.msvanalyzer.exception.TechnicalException;
import com.lsoftware.reactive.examples.msvanalyzer.model.ChatLineEvent;
import com.lsoftware.reactive.examples.msvanalyzer.services.AnalyzerService;
import com.lsoftware.reactive.examples.msvanalyzer.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AnalyzerHandler {
    @Autowired
    private Validator validator;

    @Autowired
    private AnalyzerService<ChatLineEvent> analyzerService;

    public Mono<ServerResponse> analyze(ServerRequest request){
        log.info(Constants.ANALYZER_REQUEST_RECEIVED, request.toString());
        Mono<ChatLineEvent> chatLine = request.bodyToMono(ChatLineEvent.class);

        return chatLine.flatMap(p -> {
            Errors errors = new BeanPropertyBindingResult(p, ChatLineEvent.class.getName());
            validator.validate(p, errors);

            if(errors.hasErrors()) {
                log.error(Constants.BAD_REQUEST_VALIDATION_ERRORS, errors.getFieldErrors());
                return Flux.fromIterable(errors.getFieldErrors())
                        .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                        .collectList()
                        .flatMap(list -> ServerResponse.badRequest().body(
                                Mono.just(ApiResponse.builder()
                                        .message(Constants.BAD_REQUEST)
                                        .status(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                                        .errors(list).build()), ApiResponse.class
                        ));
            } else {
                return errorHandler(analyzerService.analyze(p).flatMap(pdb -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Mono.just(ApiResponse.builder()
                                .message(Constants.SUCCESSFUL_RESPONSE)
                                .body(pdb)
                                .status(String.valueOf(HttpStatus.OK.value()))
                                .build()), ApiResponse.class)));
            }
        });
    }


    private Mono<ServerResponse> errorHandler(Mono<ServerResponse> response){
        return response.onErrorResume(error -> {

            if (error instanceof TechnicalException) {
                log.info("handling TechnicalException");
                TechnicalException exception = (TechnicalException) error;

                return ServerResponse.badRequest().body(
                        Mono.just(ApiResponse.builder()
                            .message(exception.getMessage())
                            .status(String.valueOf(exception.getStatus().value()))
                                .build()), ApiResponse.class);
            }

            return Mono.error(error);
        });
    }

}
