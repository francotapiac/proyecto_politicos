package com.project.back.services;

import com.project.back.models.NationalActuality;
import com.project.back.models.Tweet;
import com.project.back.repositories.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMethod;
import twitter4j.*;
import twitter4j.TwitterStream;
import twitter4j.StatusListener;

import javax.annotation.PostConstruct;


@CrossOrigin
@RestController
@RequestMapping("/tweets")
public class TweetService {
   @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private Twitter twitter;

   // TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();

    //Función que retorna todos los tweets almacenado en la BD
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Tweet> getAllNationalActualitys(){
        return tweetRepository.findAll();
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void run() throws TwitterException {
        /*twitterStream.addListener(new StatusListener () {
            @Override
            public void onException(Exception ex) {

            }

            public void onStatus(Status status){
                                          System.out.println(status.getText());// print tweet text to console
                                      }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {

            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {

            }

            @Override
            public void onStallWarning(StallWarning warning) {

            }
        });
        FilterQuery tweetFilterQuery = new FilterQuery(); // See
        tweetFilterQuery.track("piñera"); // OR on keywords
        tweetFilterQuery.language(new String[]{"es"}); // Note that language does not work properly on Norwegian tweets
        twitterStream.filter(tweetFilterQuery);*/

        Query query = new Query("piñera");
        QueryResult result;
        int Count=0;
        do {
            result = twitter.search(query);
            List<Status> tweets = result.getTweets();
            for (Status tweet : tweets) {
                if(tweet.getPlace() == null && tweet.getGeoLocation() == null){
                    this.create(tweet.getId(),tweet.getText(),tweet.getCreatedAt(),0,0,"","",tweet.getUser().getId(),tweet.getUser().getScreenName(),tweet.getUser().getFollowersCount(),tweet.getRetweetCount(),tweet.getUser().getName(),tweet.getFavoriteCount(),"");
                }
                else if(tweet.getPlace() == null){
                    this.create(tweet.getId(),tweet.getText(),tweet.getCreatedAt(),tweet.getGeoLocation().getLatitude(),tweet.getGeoLocation().getLongitude(),"","",tweet.getUser().getId(),tweet.getUser().getScreenName(),tweet.getUser().getFollowersCount(),tweet.getRetweetCount(),tweet.getUser().getName(),tweet.getFavoriteCount(),"");
                }
                else if(tweet.getGeoLocation() == null){
                    this.create(tweet.getId(),tweet.getText(),tweet.getCreatedAt(),0,0,tweet.getPlace().getName(),tweet.getPlace().getCountry(),tweet.getUser().getId(),tweet.getUser().getScreenName(),tweet.getUser().getFollowersCount(),tweet.getRetweetCount(),tweet.getUser().getName(),tweet.getFavoriteCount(), "");
                }
                else{
                    this.create(tweet.getId(),tweet.getText(),tweet.getCreatedAt(),tweet.getGeoLocation().getLatitude(),tweet.getGeoLocation().getLongitude(),tweet.getPlace().getName(),tweet.getPlace().getCountry(),tweet.getUser().getId(),tweet.getUser().getScreenName(),tweet.getUser().getFollowersCount(),tweet.getRetweetCount(),tweet.getUser().getName(),tweet.getFavoriteCount(),"");
                }
                System.out.println("Tweet guardado:" + "@" + tweet.getUser().getScreenName() + ":" + tweet.getText()+ "Retweet:" + tweet.getFavoriteCount());
                Count++;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        while ((query = result.nextQuery()) != null);
        System.out.println(Count);
        System.exit(0);
    }

    public Tweet create(long tweetId, String text, Date createdAt, double latitude, double longitude, String city, String country, long userId, String userName, int followersCount, int retweetCount,String realName, int favoriteCount,String sentimentAnalysis) {
        return tweetRepository.save(new Tweet(tweetId, text, createdAt, latitude, longitude, city, country, userId, userName, followersCount, retweetCount, realName, favoriteCount,sentimentAnalysis));
    }
}
