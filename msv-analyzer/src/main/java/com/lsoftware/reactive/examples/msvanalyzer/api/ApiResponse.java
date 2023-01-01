package com.lsoftware.reactive.examples.msvanalyzer.api;

import com.lsoftware.reactive.examples.msvanalyzer.model.ChatLineEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse implements Serializable {
    private ChatLineEvent body;
    private String message;
    private String status;
    private List<String> errors;
}
