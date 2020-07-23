package com.project.back.services;

import com.project.back.models.InvertedIndex;
import com.project.back.models.PoliticalParty;
import com.project.back.models.Politician;
import com.project.back.repositories.InvertedIndexRepository;
import com.project.back.repositories.PoliticalPartyRepository;
import com.project.back.repositories.PoliticianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/politicalparty")
public class PoliticalPartyService {
    @Autowired
    private PoliticalPartyRepository politicalPartyRepository;

    @Autowired
    private PoliticianRepository politicianRepository;

    @Autowired
    private InvertedIndexRepository invertedIndexRepository;

    //Mostrando todos los partidos politicos de la base de datos
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<PoliticalParty> getAllPoliticianParties(){
        return politicalPartyRepository.findAll();
    }

    //Creando ranking de partidos politicos en orden ascendente
    @RequestMapping(value="/ranking" , method = RequestMethod.GET)
    @ResponseBody
    public List<PoliticalParty> getRanking(){
        //obtener partidos politicos
        List<PoliticalParty> politicalParties = politicalPartyRepository.findAll();
        Float approbation = (float) 0;
        //para cada partido politico
        for (PoliticalParty politicalParty : politicalParties){

            String akaName = politicalParty.getAkaName();
            List<InvertedIndex> indexes = invertedIndexRepository.findByName(akaName);
            if(indexes != null){
                InvertedIndex index = indexes.get(0);
                //Calcular Aprobacion
                approbation = politicalParty.calculateApprobation(index.getTotalTweets(),
                        index.getTotalPositiveTweets(),
                        index.getTotalVeryPositiveTweets());

                //Entregando información de sentimiento de indice invertido a partido politico
                politicalParty.setCountNegative(index.getTotalNegativeTweets());
                politicalParty.setCountVeryNegative(index.getTotalVeryNegativeTweets());
                politicalParty.setCountPositive(index.getTotalPositiveTweets());
                politicalParty.setCountVeryPositive(index.getTotalVeryPositiveTweets());
            }
            politicalParty.setAprobation(approbation);
        }

        //Ordenando de mayor a menor aprobación los partidos politicos
        politicalParties.sort(Comparator.comparing(PoliticalParty::getAprobation).reversed());

        return politicalParties;
    }
}
