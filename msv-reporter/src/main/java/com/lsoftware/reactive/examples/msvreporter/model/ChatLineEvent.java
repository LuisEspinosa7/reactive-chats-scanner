package com.lsoftware.reactive.examples.msvreporter.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatLineEvent implements Serializable {

    @NotEmpty
    private String id;

    @NotEmpty
    private String chatId;

    @NotEmpty
    private String message;

    @NotNull
    private boolean checked;

    @NotNull
    private boolean available;

    @NotNull
    private boolean suspicious;

}
