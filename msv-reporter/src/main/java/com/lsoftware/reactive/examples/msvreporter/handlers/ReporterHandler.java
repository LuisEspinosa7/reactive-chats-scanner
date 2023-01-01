package com.lsoftware.reactive.examples.msvreporter.handlers;

import com.lsoftware.reactive.examples.msvreporter.api.ApiResponse;
import com.lsoftware.reactive.examples.msvreporter.entities.Suspicious;
import com.lsoftware.reactive.examples.msvreporter.exception.TechnicalException;
import com.lsoftware.reactive.examples.msvreporter.model.ChatLineEvent;
import com.lsoftware.reactive.examples.msvreporter.services.impl.ChatLineMongoService;
import com.lsoftware.reactive.examples.msvreporter.services.impl.SuspiciousMongoService;
import com.lsoftware.reactive.examples.msvreporter.utils.Constants;
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
public class ReporterHandler {
    @Autowired
    private Validator validator;

    @Autowired
    private ChatLineMongoService chatLineMongoService;

    @Autowired
    private SuspiciousMongoService suspiciousMongoService;

    public Mono<ServerResponse> suspicious(ServerRequest request){
        log.info(Constants.SUSPICIOUS_REPORTED_REQUEST, request.toString());
        Mono<ChatLineEvent> chatLine = request.bodyToMono(ChatLineEvent.class);

        return chatLine.flatMap(p -> {

            Errors errors = new BeanPropertyBindingResult(p, ChatLineEvent.class.getName());
            validator.validate(p, errors);

            if(errors.hasErrors()) {
                log.info(Constants.BAD_REQUEST_VALIDATION_ERRORS, errors.getFieldErrors());
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

                return errorHandler(
                 suspiciousMongoService.update(Suspicious
                                .builder()
                                .id(p.getId()).chatId(p.getChatId()).message(p.getMessage()).build())
                        .flatMap(pdb -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Mono.just(ApiResponse.builder()
                                .message(Constants.SUCCESSFUL_RESPONSE)
                                .body(pdb)
                                .status(String.valueOf(HttpStatus.OK.value()))
                                .build()), ApiResponse.class))
                );

            }
        });
    }


    public Mono<ServerResponse> update(ServerRequest request){
        log.info(Constants.UPDATE_REPORTED_REQUEST, request.toString());
        Mono<ChatLineEvent> chatLine = request.bodyToMono(ChatLineEvent.class);

        return chatLine.flatMap(p -> {

            Errors errors = new BeanPropertyBindingResult(p, ChatLineEvent.class.getName());
            validator.validate(p, errors);

            if(errors.hasErrors()) {
                log.info(Constants.BAD_REQUEST_VALIDATION_ERRORS, errors.getFieldErrors());
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
                return errorHandler(
                        chatLineMongoService.findById(p.getId())
                                .switchIfEmpty(Mono.error(new Exception("TASK_NOT_FOUND")))
                                .flatMap(previous -> {
                                    previous.setChecked(p.isChecked());
                                    previous.setAvailable(p.isAvailable());
                                    previous.setSuspicious(p.isSuspicious());
                                    return chatLineMongoService.update(previous)
                                            .flatMap(pdb -> ServerResponse
                                                    .ok()
                                                    .contentType(MediaType.APPLICATION_JSON)
                                                    .body(Mono.just(ApiResponse.builder()
                                                            .message(Constants.SUCCESSFUL_RESPONSE)
                                                            .body(pdb)
                                                            .status(String.valueOf(HttpStatus.OK.value()))
                                                            .build()), ApiResponse.class));
                                })
            );
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
