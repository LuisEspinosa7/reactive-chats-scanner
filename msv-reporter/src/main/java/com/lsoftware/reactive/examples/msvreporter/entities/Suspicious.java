package com.lsoftware.reactive.examples.msvreporter.entities;

import com.lsoftware.reactive.examples.msvreporter.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection= Constants.SUSPICIOUS_COLLECTION_NAME)
public class Suspicious {
    @Id
    private String id;

    @NotEmpty
    private String chatId;

    @NotEmpty
    private String message;

}
