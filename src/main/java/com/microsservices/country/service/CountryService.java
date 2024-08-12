package com.microsservices.country.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microsservices.country.dtos.CountryMedalDto;
import com.microsservices.country.models.Country;
import com.microsservices.country.models.CountryMedalInSports;
import com.microsservices.country.models.Medal;
import com.microsservices.country.repositorys.CountryMedalInSportsRepository;

@Service
public class CountryService{
    @Autowired
    private CountryMedalInSportsRepository repository;

    public ResponseEntity<CountryMedalInSports> getCountry(String name){
        try{
            List<CountryMedalInSports> country = repository.findByCountryName(name);
            return ResponseEntity.ok().body(country.get(0));

        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<List<CountryMedalDto>> getCountrys(){
        try{
            var results = repository.findCountriesAndMedals();
             Map<Country, List<Medal>> countryMedalsMap = new HashMap<>();

            for(CountryMedalInSports result : results){
                Country country = result.getCountry();
                Medal medal = result.getMedal();
    
                if (country != null) {
                    countryMedalsMap
                        .computeIfAbsent(country, k -> new ArrayList<>())
                        .add(medal);
                }
            }
            
            List<CountryMedalDto> dtos = new ArrayList<>();
            for (Map.Entry<Country, List<Medal>> entry : countryMedalsMap.entrySet()) {
                CountryMedalDto dto = new CountryMedalDto();
                dto.setCountry(entry.getKey());
                dto.setMedals(entry.getValue());
                dtos.add(dto);
            }
        return ResponseEntity.ok().body(dtos);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}