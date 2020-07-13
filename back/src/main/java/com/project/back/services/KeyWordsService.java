package com.project.back.services;

import com.project.back.models.InvertedIndex;
import com.project.back.models.KeyWords;
import com.project.back.models.Tweet;
import com.project.back.repositories.InvertedIndexRepository;
import com.project.back.repositories.KeyWordsRepository;
import com.project.back.repositories.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/keywords")
public class KeyWordsService {
    @Autowired
    private KeyWordsRepository keyWordsRepository;

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private InvertedIndexRepository invertedIndexRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<KeyWords> getAllKeyWords(){
        return keyWordsRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public void indexTweets() {
        HashMap<String, HashMap<Long, String>> result = new HashMap<>();
        //get all tweets
        List<Tweet> tweets = tweetRepository.findAll();
        //get word list
        List<KeyWords> words = keyWordsRepository.findAll();
        for (KeyWords word : words){
            result.put(word.getName(),new HashMap<Long,String>());
        }
        //para cada tweet
        for (Tweet tweet : tweets) {
            //revisar que palabras de la lista contiene el tweet
            for (KeyWords word : words) {
                //System.out.println(result.get("piñera"));
                if (tweet.getText().toLowerCase().contains(word.getName())) {
                    //conectar el id del tweet con la palabra correspondiente
                    result.get(word.getName()).put(tweet.getTweetId(), tweet.getSentimentAnalysis());
                }
            }
        }
        //guardar index en la base de datos
        List<Long> listId = new ArrayList<>();
        List<String> listSentiment = new ArrayList<>();
        for (Map.Entry<String, HashMap<Long, String>> entry : result.entrySet()){
            for (Map.Entry<Long, String> entry2 : entry.getValue().entrySet()){
                listId.add(entry2.getKey());
                listSentiment.add(entry2.getValue());
            }
            this.invertedIndexRepository.save(new InvertedIndex(entry.getKey(),listId,listSentiment));
            listId.clear();
            listSentiment.clear();
        }
    }
}
