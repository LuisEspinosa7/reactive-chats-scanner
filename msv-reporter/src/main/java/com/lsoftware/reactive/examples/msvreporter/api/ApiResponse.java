package com.lsoftware.reactive.examples.msvreporter.api;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class ApiResponse<T> implements Serializable {
    private T body;
    private String message;
    private String status;
    private List<String> errors;
}
