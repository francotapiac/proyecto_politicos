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
import java.util.List;

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
        //HashMap<String, HashMap<Long, String>> result = new HashMap<>();
        //get all tweets
        List<Tweet> tweets = tweetRepository.findAll();
        //get word list
        List<KeyWords> words = keyWordsRepository.findAll();

        /*for (KeyWords word : words){
            result.put(word.getName(),new HashMap<Long,String>());
        }

         */
        List<Long> listId = new ArrayList<>();
        List<String> listSentiment = new ArrayList<>();
        Integer id = 1;
        Integer pos = 0;
        Integer verypos = 0;
        Integer neg = 0;
        Integer veryneg = 0;
        Integer total = 0;
        String sentiment;
        //para cada palabra de las keywords
        for (KeyWords word : words) {
            //para cada tweet
            for (Tweet tweet : tweets) {

                if (tweet.getText().toLowerCase().contains(word.getName())) {
                    //conectar el id del tweet con la palabra correspondiente
                    //result.get(word.getName()).put(tweet.getTweetId(), tweet.getSentimentAnalysis());
                    listId.add(tweet.getTweetId());
                    sentiment = tweet.getSentimentAnalysis();
                    listSentiment.add(sentiment);
                    if (sentiment.equals("negative")){neg++;}
                    if (sentiment.equals("very negative")){veryneg++;}
                    if (sentiment.equals("positive")){pos++;}
                    if (sentiment.equals("very positive")){verypos++;}
                }
            }
            total = pos + verypos + neg + veryneg;
            this.invertedIndexRepository.save(new InvertedIndex(id.toString(), word.getName(),listId,listSentiment,total,neg,veryneg,pos,verypos));
            pos = 0;
            verypos = 0;
            neg = 0;
            veryneg = 0;
            id++;
            listId.clear();
            listSentiment.clear();
        }
        /*
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

         */
    }
}
