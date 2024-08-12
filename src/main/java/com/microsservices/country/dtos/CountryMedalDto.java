
package com.microsservices.country.dtos;

import java.util.List;

import com.microsservices.country.models.Country;
import com.microsservices.country.models.Medal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryMedalDto {
    private Country country;
    private List<Medal> medals;

    public CountryMedalDto(){

    }
    public CountryMedalDto(Country country, List<Medal> medals){
        this.country = country;
        this.medals = medals;
    }
}