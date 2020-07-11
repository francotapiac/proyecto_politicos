package com.project.back.services;

import com.project.back.models.Tweet;
import com.project.back.repositories.TweetRepository;
import com.project.back.sentimentAnalysis.SentimentAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.*;
import twitter4j.*;

import java.util.*;


@CrossOrigin
@RestController
@RequestMapping("/tweets")
public class TweetService {
   @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private Twitter twitter;
    private SentimentAnalyzer sentimentAnalyzer;

   // TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();

    //Retorna todos los tweets almacenado en la BD
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Tweet> getAllTweets(){
        String text_ejemplo = "Odio al Presidente y lo quiero matar";
        sentimentAnalyzer = new SentimentAnalyzer();
        HashMap<String, Double> clasificacion = sentimentAnalyzer.getClasification(text_ejemplo);
        clasificacion.forEach((k,v) -> System.out.println("Key: " + k + ": Value: " + v));
        return tweetRepository.findAll();
    }

    //Retorna una lista ordenada según Retweet de todos los tweet
    @RequestMapping(value = "/sort", method = RequestMethod.GET)
    @ResponseBody
    public List<Tweet> allSort(){
        return  this.sortByRetweet();
    }

    //Retorna una lista ordenada según Retweet de count tweets, siendo count un entero de 0 a n.
    @RequestMapping(value = "/sort/{count}", method = RequestMethod.GET)
    @ResponseBody
    public List<Tweet> searchCount(@PathVariable Integer count){

        List<Tweet> tweetSort = this.sortByRetweet();   //Obtiene tweets ordenados según Retweet
        List<Tweet> newTweetSort = new ArrayList<Tweet>();

        if(count > tweetSort.size()){                   //Revisa que count no exceda el largo de la lista de tweets
            count = tweetSort.size();
        }

        for(int i = 0; i < count; i++){                 //Se recorre y agrega una cantidad count de tweets
            newTweetSort.add(tweetSort.get(i));
        }
        return newTweetSort;
    }

    //Entrega una lista de tweets según una lista de palabras
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void run() throws TwitterException {


        Query query = new Query("piñera");
        QueryResult result;
        int Count=0;
        do {
            result = twitter.search(query);
            List<Status> tweets = result.getTweets();
            for (Status tweet : tweets) {

                //Obtención de ruta de perfil
                String perfilUser = this.perfilUser(tweet.getUser().getScreenName());

                //Obtención de ruta de twitter
                String tweetURL = this.TweetURL(tweet.getId(),tweet.getUser().getScreenName());

                //Verificación si tweet es Retweet o un tweet.  Esto se hace para obtener el texto completo y no con ...
                //y para obtener la cantidad de RT por tweet o retweet
                String tweetText;
                int retweetCount;
                //En caso de ser Tweet, se obtiene de getText.
                if(tweet.getRetweetedStatus() == null){
                    tweetText = tweet.getText();
                    retweetCount = tweet.getRetweetCount();
                }
                // En caso de ser RT, entonces el texto se obtendrá de getRetweetedStatus.
                else{
                    tweetText = tweet.getRetweetedStatus().getText();
                    retweetCount = tweet.getRetweetedStatus().getRetweetCount();
                }


                //Se crean los tweets verificando si existen o no las geolocalizaciones y los lugares.
                if(tweet.getPlace() == null && tweet.getGeoLocation() == null){
                    this.create(tweet.getId(),tweetText,tweet.getCreatedAt(),0,0,"","",tweet.getUser().getId(),tweet.getUser().getScreenName(),tweet.getUser().getFollowersCount(),retweetCount,tweet.getUser().getName(),tweet.getFavoriteCount(),tweet.getUser().getProfileImageURLHttps(),perfilUser,tweetURL,"");
                }
                else if(tweet.getPlace() == null){
                    this.create(tweet.getId(),tweetText,tweet.getCreatedAt(),tweet.getGeoLocation().getLatitude(),tweet.getGeoLocation().getLongitude(),"","",tweet.getUser().getId(),tweet.getUser().getScreenName(),tweet.getUser().getFollowersCount(),retweetCount,tweet.getUser().getName(),tweet.getFavoriteCount(),tweet.getUser().getProfileImageURLHttps(),perfilUser,tweetURL,"");
                }
                else if(tweet.getGeoLocation() == null){
                    this.create(tweet.getId(),tweetText,tweet.getCreatedAt(),0,0,tweet.getPlace().getName(),tweet.getPlace().getCountry(),tweet.getUser().getId(),tweet.getUser().getScreenName(),tweet.getUser().getFollowersCount(),retweetCount,tweet.getUser().getName(),tweet.getFavoriteCount(),tweet.getUser().getProfileImageURLHttps(),perfilUser,tweetURL, "");
                }
                else{
                    this.create(tweet.getId(),tweetText,tweet.getCreatedAt(),tweet.getGeoLocation().getLatitude(),tweet.getGeoLocation().getLongitude(),tweet.getPlace().getName(),tweet.getPlace().getCountry(),tweet.getUser().getId(),tweet.getUser().getScreenName(),tweet.getUser().getFollowersCount(),retweetCount,tweet.getUser().getName(),tweet.getFavoriteCount(),tweet.getUser().getProfileImageURLHttps(),perfilUser,tweetURL,"");
                }
                System.out.println("Tweet guardado:" + "@" + tweet.getUser().getScreenName() + ":" + tweetText + " Ruta perfil: " +perfilUser + " Ruta tweet: " +tweetURL);
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

    public Tweet create(long tweetId, String text, Date createdAt, double latitude, double longitude, String city, String country, long userId, String userName, int followersCount, int retweetCount,String realName, int favoriteCount,String profileImage,String perfilUser, String tweetURL,String sentimentAnalysis) {
        return tweetRepository.save(new Tweet(tweetId, text, createdAt, latitude, longitude, city, country, userId, userName, followersCount, retweetCount, realName, favoriteCount,profileImage,perfilUser,tweetURL,sentimentAnalysis));
    }

    public String perfilUser(String userName){
        return "https://twitter.com/" + userName;
    }

    public String TweetURL(long idTWeet,String userName){
        return "https://twitter.com/" + userName + "/status/" + idTWeet;
    }

    public List<Tweet> sortByRetweet(){
        List<Tweet> tweetSort = tweetRepository.findAll();
        tweetSort.sort(Comparator.comparing(Tweet::getRetweetCount)
                .reversed()
        );
        return  tweetSort;
    }

}
