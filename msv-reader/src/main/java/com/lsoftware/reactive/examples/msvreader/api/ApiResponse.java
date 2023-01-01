package com.lsoftware.reactive.examples.msvreader.api;

import com.lsoftware.reactive.examples.msvreader.entities.ChatLine;
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
    private ChatLine body;
    private String message;
    private String status;
    private List<String> errors;
}
