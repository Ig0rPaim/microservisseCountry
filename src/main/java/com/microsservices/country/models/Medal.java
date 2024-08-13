package com.microsservices.country.models;

import com.microsservices.country.dtos.Medal_PostDto;
import com.microsservices.country.enums.MedalType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name="medals")
@Getter
@Setter
public class Medal{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private MedalType type;
    public Medal() {
    }
    public Medal(Medal_PostDto m) {
        this.type = m.type();
    }
    public Medal(Long id, MedalType type) {
        this.id = id;
        this.type = type;
    }
    

    
}