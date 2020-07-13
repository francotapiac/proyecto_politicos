package com.project.back.models;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Document(collection="InvertedIndex")
public class InvertedIndex {
    @Id
    public String _id;
    public String name;
    public List<Long> listId;
    public List<String> listSentiment;

    public InvertedIndex(){};

    public InvertedIndex(String name, List<Long> listId, List<String> listSentiment) {
        this.name = name;
        this.listId = listId;
        this.listSentiment = listSentiment;
    }
}
