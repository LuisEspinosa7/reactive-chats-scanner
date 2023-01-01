package com.lsoftware.reactive.examples.msvchatgenerator.providers.impl;

import com.lsoftware.reactive.examples.msvchatgenerator.entities.ChatLine;
import com.lsoftware.reactive.examples.msvchatgenerator.providers.SequentialProvider;
import com.lsoftware.reactive.examples.msvchatgenerator.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

@Component
@Slf4j
public class ChatLineProvider extends SequentialProvider<ChatLine, Scanner> {

    public ChatLineProvider(Scanner sourceScanner){
        super(sourceScanner);
    }

    @Override
    protected Optional<ChatLine> processNext() {
        if (!source.hasNext()){
            return Optional.empty();
        }
        String[] nextLine = source.nextLine().split(Constants.COLON);
        String[] messageArray = Arrays.copyOfRange(nextLine, 1, nextLine.length);
        String message = Arrays.toString(messageArray).replace(Constants.KEY_OPENED, Constants.EMPTY).replace(Constants.KEY_CLOSED, Constants.EMPTY);

        return Optional.of(ChatLine.builder()
                .chatId(nextLine[Constants.ZERO])
                .message(message)
                .available(true)
                .checked(false)
                .build());
    }

}
