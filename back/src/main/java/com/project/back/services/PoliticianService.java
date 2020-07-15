package com.project.back.services;

import com.project.back.models.InvertedIndex;
import com.project.back.models.Politician;
import com.project.back.repositories.InvertedIndexRepository;
import com.project.back.repositories.PoliticianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/politician")
public class PoliticianService {
    @Autowired
    private PoliticianRepository politicianRepository;
    @Autowired
    private InvertedIndexRepository invertedIndexRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Politician> getAllPoliticians(){
        return politicianRepository.findAll();
    }

    @RequestMapping(value="/ranking" , method = RequestMethod.GET)
    @ResponseBody
    public List<Politician> getRanking(){
        //obtener politicos
        List<Politician> politicians = politicianRepository.findAll();
        Float approbation = (float) 0;
        //para cada politico
        for (Politician politician : politicians){

            String akaName = politician.getAkaName();
            List<InvertedIndex> indexes = invertedIndexRepository.findByName(akaName);
            if(indexes != null){
                InvertedIndex index = indexes.get(0);
                //Calcular Aprobacion
                approbation = politician.calculateApprobation(index.getTotalTweets(),
                                                                index.getTotalPositiveTweets(),
                                                                index.getTotalVeryPositiveTweets());

                politician.setCountNegative(index.getTotalNegativeTweets());
                politician.setCountVeryNegative(index.getTotalVeryNegativeTweets());
                politician.setCountPositive(index.getTotalPositiveTweets());
                politician.setCountVeryPositive(index.getTotalVeryPositiveTweets());
            }
            politician.setAprobation(approbation);
        }
        politicians.sort(Comparator.comparing(Politician::getAprobation).reversed());

        return politicians;
    }
}
