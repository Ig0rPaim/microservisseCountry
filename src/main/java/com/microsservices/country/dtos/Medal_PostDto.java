package com.microsservices.country.dtos;

import com.microsservices.country.enums.MedalType;
import com.microsservices.country.models.Medal;

public record Medal_PostDto(String id,MedalType type) {
    public Medal_PostDto(Medal m){
        this(m.getId().toString(), m.getType());
    }
}
