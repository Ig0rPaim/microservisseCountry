package com.microsservices.country.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microsservices.country.dtos.CountryMedalDto;
import com.microsservices.country.dtos.CountryMedalInSportsDto;
import com.microsservices.country.dtos.MedalDto;
import com.microsservices.country.models.Country;
import com.microsservices.country.models.CountryMedalInSports;
import com.microsservices.country.models.Medal;
import com.microsservices.country.models.Sport;
import com.microsservices.country.repositorys.CountryMedalInSportsRepository;

@Service
public class CountryService{
    @Autowired
    private CountryMedalInSportsRepository repository;

    public ResponseEntity<CountryMedalInSportsDto> getCountry(String name){
        try{
            List<CountryMedalInSports> results = repository.findByCountryName(name);
            Map<Medal, List<Sport>> medalsSportMap = new HashMap<>();
            CountryMedalInSportsDto countryMedalInSportDto = new CountryMedalInSportsDto();
            for(CountryMedalInSports result : results){
                Medal medal = result.getMedal();
                Sport sport = result.getSport();
                 
                if(medal != null){
                    medalsSportMap
                        .computeIfAbsent(medal, k -> new ArrayList<>())
                        .add(sport);
                }
            }
            List<MedalDto> medalDtos = new ArrayList<>();
            for(Map.Entry<Medal, List<Sport>> entry : medalsSportMap.entrySet()){
                MedalDto dto = new MedalDto();
                dto.setMedal(entry.getKey());
                dto.setSports(entry.getValue());
                medalDtos.add(dto);
            }
            countryMedalInSportDto.setCountry(results.get(0).getCountry());
            countryMedalInSportDto.setMedals(medalDtos);


            return ResponseEntity.ok().body(countryMedalInSportDto);

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