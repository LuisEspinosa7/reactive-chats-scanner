package com.lsoftware.reactive.examples.msvanalyzer.model;

import com.lsoftware.reactive.examples.msvanalyzer.utils.Constants;
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

    @NotEmpty(message = Constants.FIELD_REQUIRED)
    private String id;

    @NotEmpty(message = Constants.FIELD_REQUIRED)
    private String chatId;

    @NotEmpty(message = Constants.FIELD_REQUIRED)
    private String message;

    @NotNull(message = Constants.FIELD_REQUIRED)
    private boolean checked;

    @NotNull(message = Constants.FIELD_REQUIRED)
    private boolean available;

    @NotNull
    private boolean suspicious;

}
