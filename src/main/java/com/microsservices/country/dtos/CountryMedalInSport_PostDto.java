package com.microsservices.country.dtos;

import com.microsservices.country.models.Country;
import com.microsservices.country.models.Medal;
import com.microsservices.country.models.Sport;

public record CountryMedalInSport_PostDto(Country country, Medal medal, Sport sport) {}
