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
    public Integer totalTweets;
    public Integer totalVeryPositiveTweets;
    public Integer totalPositiveTweets;
    public Integer totalNegativeTweets;
    public Integer totalVeryNegativeTweets;


    public InvertedIndex(){};

    public InvertedIndex(String id, String name, List<Long> listId, List<String> listSentiment, Integer total,
                         Integer totalverypos, Integer totalveryneg, Integer totalneg, Integer totalpos) {
        this._id = id;
        this.name = name;
        this.listId = listId;
        this.listSentiment = listSentiment;
        this.totalTweets = total;
        this.totalNegativeTweets = totalneg;
        this.totalVeryNegativeTweets = totalveryneg;
        this.totalPositiveTweets = totalpos;
        this.totalVeryPositiveTweets = totalverypos;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public List<Long> getListId() {
        return listId;
    }

    public List<String> getListSentiment() {
        return listSentiment;
    }

    public Integer getTotalTweets() {
        return totalTweets;
    }

    public Integer getTotalVeryPositiveTweets() {
        return totalVeryPositiveTweets;
    }

    public Integer getTotalPositiveTweets() {
        return totalPositiveTweets;
    }

    public Integer getTotalNegativeTweets() {
        return totalNegativeTweets;
    }

    public Integer getTotalVeryNegativeTweets() {
        return totalVeryNegativeTweets;
    }
}
