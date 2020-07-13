package com.project.back.services;

import com.project.back.models.Politician;
import com.project.back.repositories.InvertedIndexRepository;
import com.project.back.repositories.PoliticianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
        //para cada politico
            //obtener los sentimientos
            //calcular aprobación en base a sentimiento
            //retornar politicos
        return politicianRepository.findAll();
    }
}
