package com.lsoftware.reactive.examples.msvanalyzer.config;

import com.lsoftware.reactive.examples.msvanalyzer.utils.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableRetry
public class AppConfig {
    @Value("${app.input.file.forbidden}")
    private String forbiddenWordsFile;

    @Bean
    public List<String> forbiddenWords(){
        List<String> words = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(forbiddenWordsFile))){
            String line;
            while((line = br.readLine()) != null){
                words.add(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(Constants.FORBIDDEN_FILE_NOT_FOUND, e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return words;
    }

}
