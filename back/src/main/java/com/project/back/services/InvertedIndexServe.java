package com.project.back.services;

import com.project.back.models.Tweet;
import com.project.back.repositories.TweetRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InvertedIndexServe {
    //Map<String, List<Integer>> index = new HashMap<String, List<Integer>>();
    private TweetRepository tweetRepository;

    public void indexTweets(){
        //List<Tuple> listIds = new ArrayList<Tuple>();
        HashMap<String, HashMap<Long, String>> result = new HashMap<>();

        //get all tweets
        List<Tweet> tweets = tweetRepository.findAll();
        //get word list
        List<String> words = new ArrayList<String>();
        //para cada tweet
        for (Tweet tweet : tweets){
            //revisar que palabras de la lista contiene el tweet
            for (int i = 0; i < words.size(); i++){
                if(tweet.getText().contains(words.get(i))){
                    //conectar el id del tweet con la palabra correspondiente
                    result.get(words.get(i)).put(tweet.getTweetId(), tweet.getSentimentAnalysis());
                }
            }
        }
        //guardar en la base de datos


    }

}
