package com.microsservices.country.dtos;

import java.util.List;

import com.microsservices.country.models.Country;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CountryMedalInSportsDto {
    private Country country;
    private List<MedalDto> medals;
    
}
