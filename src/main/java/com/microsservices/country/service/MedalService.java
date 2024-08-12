
package com.microsservices.country.service;

import java.net.URI;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.microsservices.country.repositorys.MedalRepository;
import com.microsservices.country.repositorys.SportRepository;

import jakarta.transaction.Transactional;

import com.microsservices.country.repositorys.CountryMedalInSportsRepository;
import com.microsservices.country.repositorys.CountryRespository;

import com.microsservices.country.dtos.CountryMedalInSport_PostDto;
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
    public ResponseEntity postMedals(CountryMedalInSport_PostDto entity){
        try{
            Country country = entity.country();
            Medal medal = entity.medal();
            Sport sport = entity.sport();
            CountryMedalInSports countryMedalInSports = 
                new CountryMedalInSports(sport, medal, country);
            CountryMedalInSports retorno = saveEntitys(country, medal, sport, countryMedalInSports);
            return buildReturn(retorno);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    private CountryMedalInSports saveEntitys(Country country, Medal medal, Sport sport, CountryMedalInSports csm){
        countryRespository.save(country);
        medalRepository.save(medal);
        sportRepository.save(sport);
        return csmRepository.save(csm);
    }

    private ResponseEntity buildReturn(CountryMedalInSports csm){
            URI location = UriComponentsBuilder
                            .fromPath("/api/Country/{name}")
                            .buildAndExpand(csm.getCountry().getName())
                            .toUri();
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(location);
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
    
}