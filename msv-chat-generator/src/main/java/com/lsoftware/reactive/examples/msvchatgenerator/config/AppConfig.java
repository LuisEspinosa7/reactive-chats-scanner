package com.lsoftware.reactive.examples.msvchatgenerator.config;

import com.lsoftware.reactive.examples.msvchatgenerator.utils.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

@Configuration
public class AppConfig {
    @Value("${app.input.file.source}")
    private String sourceFile;

    @Bean
    public Scanner chatLineScanner(){
        Scanner scanner;
        try {
            File inputFile = new File(sourceFile);
            scanner = new Scanner(new FileReader(inputFile));
            scanner.nextLine();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(Constants.SOURCE_FILE_NOT_FOUND, e);
        }
        return scanner;
    }

}
