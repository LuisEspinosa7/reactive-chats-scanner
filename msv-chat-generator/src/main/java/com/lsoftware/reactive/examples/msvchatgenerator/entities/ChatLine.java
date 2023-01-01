package com.lsoftware.reactive.examples.msvchatgenerator.entities;

import com.lsoftware.reactive.examples.msvchatgenerator.utils.Constants;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Document(collection= Constants.CHATS_COLLECTION_NAME)
public class ChatLine {
    @Id
    private String id;

    @NotEmpty
    private String chatId;

    @NotEmpty
    private String message;

    @NotEmpty
    private boolean checked;

    @NotEmpty
    private boolean available;

    @NotNull
    private boolean suspicious;
}
