package com.microsservices.country.models;

import com.microsservices.country.dtos.SportDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name="sports")
@Getter
@Setter
public class Sport{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    public Sport() {
    }
    public Sport(SportDto s) {
        this.name = s.name();
        this.description = s.description();
    }
    
}