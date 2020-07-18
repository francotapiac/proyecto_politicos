package com.project.back.sentimentAnalysis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SentimentAnalyzerConfiguration {
    @Bean
    public SentimentClassifier sentimentClassifier() {
        return new SentimentClassifier();
    }
}