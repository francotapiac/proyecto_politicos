package com.project.back.sentimentAnalysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class SentimentAnalyzer {

    //Atributes
    @Autowired
    private SentimentClassifier sentimentClassifier;

    //Methods
    public HashMap<String, Double> getClasification(String text){
        //sentimentClassifier = new SentimentClassifier();
        return this.sentimentClassifier.classify(text);
    }

    //Getters
    public SentimentClassifier getClassifier() {
        return sentimentClassifier;
    }

    //Setters
    public void setClassifier(SentimentClassifier classifier) {
        this.sentimentClassifier = classifier;
    }
}
