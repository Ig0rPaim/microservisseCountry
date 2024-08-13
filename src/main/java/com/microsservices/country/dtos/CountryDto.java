package com.microsservices.country.dtos;

import com.microsservices.country.models.Country;

public record CountryDto(String name, byte[] flag) {
    public CountryDto(Country c){
        this(c.getName(), c.getFlag());
    }    
}
