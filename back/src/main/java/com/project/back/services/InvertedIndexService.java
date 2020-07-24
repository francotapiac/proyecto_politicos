package com.project.back.services;

import com.project.back.models.InvertedIndex;
import com.project.back.models.PoliticalParty;
import com.project.back.models.Politician;
import com.project.back.models.Tweet;
import com.project.back.repositories.InvertedIndexRepository;
import com.project.back.repositories.PoliticalPartyRepository;
import com.project.back.repositories.PoliticianRepository;
import com.project.back.repositories.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/invertedindex")
public class InvertedIndexService {
    @Autowired
    private PoliticianRepository politicianRepository;
    @Autowired
    private PoliticalPartyRepository politicalPartyRepository;

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private InvertedIndexRepository invertedIndexRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<InvertedIndex> getCompleteInvertedIndex(){
        return invertedIndexRepository.findAll();
    }

    //Variable global
    Integer id = 1;                                         //id para guardar el objeto en la base de datos (Mongodb)

    //Crear indice invertido para los politicos
    @RequestMapping(method = RequestMethod.POST,value = "/politician")
    @ResponseBody
    public void indexTweetsPolitician() {
        //obtener todos los tweets
        List<Tweet> tweets = tweetRepository.findAll();
        //obtener todos los politicos
        List<Politician> politicians = politicianRepository.findAll();

        //Se inicializan las variables a utilizar
        List<Long> listId = new ArrayList<>();                  //Lista para guardar los id de los tweets
        List<String> listSentiment = new ArrayList<>();         //Lista para guardar los string de sentimiento de los tweets

        Integer pos = 0;                                        //contador de tweets positivos
        Integer verypos = 0;                                    //contador de tweets muy positivos
        Integer neg = 0;                                        //contador de tweets negativos
        Integer veryneg = 0;                                    //contador de tweets muy negativos
        Integer total = 0;                                      //contador de tweets (total)
        String sentiment;                                       //String utilizado para guardar el sentimiento del tweet actual

        //para cada politico
        for (Politician politician : politicians) {
            //para cada tweet
            for (Tweet tweet : tweets) {
                //Si el tweet contiene el nombre del politico
                if (tweet.getText().toLowerCase().contains(politician.getAkaName())) {
                    //conectar el id del tweet con la palabra correspondiente
                    listId.add(tweet.getTweetId());
                    sentiment = tweet.getSentimentAnalysis();
                    listSentiment.add(sentiment);
                    if (sentiment.equals("negative")){neg++;}
                    if (sentiment.equals("very negative")){veryneg++;}
                    if (sentiment.equals("positive")){pos++;}
                    if (sentiment.equals("very positive")){verypos++;}
                }
            }
            //Calcular total del tweets
            total = pos + verypos + neg + veryneg;
            //Guardar indice en la base de datos (Mongodb)
            this.invertedIndexRepository.save(new InvertedIndex(id.toString(), politician.getAkaName(),listId,listSentiment,total,neg,veryneg,pos,verypos));

            //Reiniciar variables
            pos = 0;
            verypos = 0;
            neg = 0;
            veryneg = 0;
            id++;
            listId.clear();
            listSentiment.clear();
        }
    }

    //Crear indice invertido para los partidos politicos
    @RequestMapping(method = RequestMethod.POST,value = "/party")
    @ResponseBody
    public void indexTweetsPoliticalParty() {
        //obtener todos los tweets
        List<Tweet> tweets = tweetRepository.findAll();
        //obtener todos los partidos politicos
        List<PoliticalParty> politicianPartys = politicalPartyRepository.findAll();

        //Se inicializan las variables a utilizar
        List<Long> listId = new ArrayList<>();                  //Lista para guardar los id de los tweets
        List<String> listSentiment = new ArrayList<>();         //Lista para guardar los string de sentimiento de los tweets
        Integer pos = 0;                                        //contador de tweets positivos
        Integer verypos = 0;                                    //contador de tweets muy positivos
        Integer neg = 0;                                        //contador de tweets negativos
        Integer veryneg = 0;                                    //contador de tweets muy negativos
        Integer total = 0;                                      //contador de tweets (total)
        String sentiment;                                       //String utilizado para guardar el sentimiento del tweet actual

        //para cada partido politico
        for (PoliticalParty politicalParty : politicianPartys) {
            //para cada tweet
            for (Tweet tweet : tweets) {
                //Si el tweet contiene el nombre del partido politico, se realiza analisis de sentimiento del tweet
                if (tweet.getText().toLowerCase().contains(politicalParty.getAkaName())) {
                    //conectar el id del tweet con la palabra correspondiente
                    listId.add(tweet.getTweetId());
                    sentiment = tweet.getSentimentAnalysis();
                    listSentiment.add(sentiment);
                    if (sentiment.equals("negative")){neg++;}
                    if (sentiment.equals("very negative")){veryneg++;}
                    if (sentiment.equals("positive")){pos++;}
                    if (sentiment.equals("very positive")){verypos++;}
                }
            }
            //Calcular total del tweets
            total = pos + verypos + neg + veryneg;
            //Guardar indice en la base de datos (Mongodb)
            this.invertedIndexRepository.save(new InvertedIndex(id.toString(), politicalParty.getAkaName(),listId,listSentiment,total,neg,veryneg,pos,verypos));

            //Reiniciar variables
            pos = 0;
            verypos = 0;
            neg = 0;
            veryneg = 0;
            id++;
            listId.clear();
            listSentiment.clear();
        }
    }
}
