package com.microsservices.country.dtos;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CountryMedalInSportsDto {
    private CountryDto country;
    private List<MedalSportsDto> medals;
    
}
