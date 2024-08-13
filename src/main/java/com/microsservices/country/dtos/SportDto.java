package com.microsservices.country.dtos;

import com.microsservices.country.models.Sport;

public record SportDto(String id, String name, String description) {
    public SportDto(Sport s){
        this(s.getId().toString(), s.getName(), s.getDescription());
    }
}
