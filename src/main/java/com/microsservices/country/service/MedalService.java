
package com.microsservices.country.service;

import java.net.URI;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.microsservices.country.repositorys.MedalRepository;
import com.microsservices.country.repositorys.SportRepository;

import jakarta.transaction.Transactional;

import com.microsservices.country.repositorys.CountryMedalInSportsRepository;
import com.microsservices.country.repositorys.CountryRespository;

import com.microsservices.country.dtos.CountryMedalInSport_PostDto;
import com.microsservices.country.enums.MedalType;
import com.microsservices.country.models.Country;
import com.microsservices.country.models.CountryMedalInSports;
import com.microsservices.country.models.Medal;
import com.microsservices.country.models.Sport;

@Service
public class MedalService {

    @Autowired
    MedalRepository medalRepository;
    @Autowired
    SportRepository sportRepository;
    @Autowired
    CountryRespository countryRespository;
    @Autowired
    CountryMedalInSportsRepository csmRepository;

    @Transactional
    public ResponseEntity<Void> postMedals(CountryMedalInSport_PostDto entity){
        try{
            Country country = new Country(entity.country());
            Medal medal = new Medal(entity.medal());
            Sport sport = new Sport(entity.sport());
            CountryMedalInSports retorno = saveEntitys(country, medal, sport);
            return buildReturn(retorno);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    private CountryMedalInSports saveEntitys(Country country, Medal medal, Sport sport) throws Exception{
        Country c = findCountry(country); 
        Medal m = findMedal(medal);
        Sport s = findSport(sport);
        return csmRepository.save(new CountryMedalInSports(s, m, c)); //VERIFICAR SE JÁ EXISTE ANTES DE SALVAR
    }

    private ResponseEntity<Void> buildReturn(CountryMedalInSports csm){
            URI location = UriComponentsBuilder
                            .fromPath("/api/Country/{name}")
                            .buildAndExpand(csm.getCountry().getName())
                            .toUri();
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(location);
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    private Country findCountry(Country c) throws Exception{
        Country country =  countryRespository.findByName(c.getName());
        if(country == null)
            throw new Exception("country not find");
        return country;
    }

    private Sport findSport(Sport s) throws Exception{
        Sport sport = sportRepository.findByName(s.getName());
        if(sport == null)
            throw new Exception("sport not find");
        return sport;
    }

    private Medal findMedal(Medal m){
        switch (m.getType()) {
            case OURO:
                return new Medal(1L, MedalType.OURO);
            case PRATA:
                return new Medal(2L, MedalType.PRATA);
            case BRONZE:
                return new Medal(3L, MedalType.BRONZE);
            default:
                throw new IllegalArgumentException("Tipo de medalha desconhecido: " + m.getType());
        }
    }
    
}