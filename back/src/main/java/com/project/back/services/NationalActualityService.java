package com.project.back.services;

import com.project.back.models.NationalActuality;
import com.project.back.repositories.NationalActualityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/nationals")
@CrossOrigin(origins = "*")
public class NationalActualityService {
    @Autowired
    private NationalActualityRepository nationalActualityRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<NationalActuality> getAllNationalActualitys(){
        return nationalActualityRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public NationalActuality getNationalActualityById(@PathVariable Integer id) {
        return nationalActualityRepository.findNationalActualityById(id);
    }
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity createNational(@RequestBody NationalActuality national) {
        NationalActuality nationalActuality = new NationalActuality();
        nationalActuality.setId(national.getId());
        nationalActuality.setName(national.getName());
        nationalActuality.setDescription(national.getDescription());
        nationalActuality.setCantidad(national.getCantidad());
        return new ResponseEntity(nationalActualityRepository.save(nationalActuality),HttpStatus.CREATED);
    }

}
