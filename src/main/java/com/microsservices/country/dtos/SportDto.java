package com.microsservices.country.dtos;

import com.microsservices.country.models.Sport;

public record SportDto(String name, String description) {
    public SportDto(Sport s){
        this(s.getName(), s.getDescription());
    }
}
