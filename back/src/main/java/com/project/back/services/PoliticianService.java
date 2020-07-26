package com.project.back.services;

import com.project.back.models.InvertedIndex;
import com.project.back.models.PoliticalParty;
import com.project.back.models.Politician;
import com.project.back.repositories.InvertedIndexRepository;
import com.project.back.repositories.PoliticalPartyRepository;
import com.project.back.repositories.PoliticianRepository;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/politician")
public class PoliticianService {
    @Autowired
    private PoliticianRepository politicianRepository;

    @Autowired
    private PoliticalPartyRepository politicalPartyRepository;

    @Autowired
    private InvertedIndexRepository invertedIndexRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Politician> getAllPoliticians(){
        System.out.println("aka:" + politicianRepository.findAll().get(0).getPoliticalParty());
        return politicianRepository.findAll();
    }

    //Obteniendo aprobaciones de los partidos politicos, según la aprobacion total de los politicos pertenecientes a cada partido
    @RequestMapping(value="/politicalPartyRanking",  method = RequestMethod.GET)
    @ResponseBody
    public List<PoliticalParty> getPoliticalPartyRanking(){
        List<PoliticalParty> politicalParties = politicalPartyRepository.findAll();
        //Se calcula la aprobación de los politicos
        this.getRanking();
        //Para cada partido politico, se obtiene la lista de politicos relacionados
        for(PoliticalParty politicalParty : politicalParties){
            //Obteniendo politicos por partido politico
            List<Politician> politicians = politicalParty.getPoliticians();
            Integer positive = 0;
            Integer veryPositive = 0;
            Integer negative = 0;
            Integer veryNegative = 0;
            Integer sizePoliticians = politicians.size();
            //Se recorre cada politico del partido politico
            for(Politician politician: politicians){
                positive = positive + politician.getCountPositive();
                veryPositive = veryPositive + politician.getCountVeryPositive();
                negative = negative + politician.getCountNegative();
                veryNegative = veryNegative + politician.getCountVeryNegative();
            }
            //Modificando valores de aprobacion en partido politico
            this.setCountPoliticalParty(politicalParty,positive,veryPositive,negative,veryNegative,sizePoliticians);
        }
        //Modificando ranking de politicos segun aprobacion
        politicalParties.sort(Comparator.comparing(PoliticalParty::getAprobation).reversed());
        return politicalParties;
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

    @PostMapping("/create")
    @ResponseBody
    public Politician createPolitician(@RequestBody Politician resource) throws ParseException{
        List<PoliticalParty> politicalParties = politicalPartyRepository.findAll();
        Politician newPolicitian = new Politician();
        newPolicitian.setRealName(resource.getRealName());
        newPolicitian.setAkaName(resource.getAkaName());
        newPolicitian.setAprobation(resource.getAprobation());
        newPolicitian.setAprobationActuality(resource.getAprobationActuality());
        newPolicitian.setDescription(resource.getDescription());
        newPolicitian.setUrlImage(resource.getUrlImage());

        politicalParties = politicalPartyRepository.findAll();
        newPolicitian.setPoliticalParty(politicalParties.get(0));

        return politicianRepository.save(newPolicitian);
    }


    // Modificar los contadores de aprobación
    public PoliticalParty setCountPoliticalParty(PoliticalParty politicalParty,Integer positive,Integer veryPositive, Integer negative, Integer veryNegative, int sizePoliticians){

        politicalParty.setCountPositive(positive);
        politicalParty.setCountVeryPositive(veryPositive);
        politicalParty.setCountNegative(negative);
        politicalParty.setCountVeryNegative(veryNegative);
        int positiveSum = positive + veryPositive;
        int negativeSum = negative + veryNegative;
        int total = positiveSum + negativeSum;
        if(total != 0)
            politicalParty.setAprobation((positiveSum*100)/(negativeSum + positiveSum));
        else
            politicalParty.setAprobation(0);


        return politicalParty;
    }


}
