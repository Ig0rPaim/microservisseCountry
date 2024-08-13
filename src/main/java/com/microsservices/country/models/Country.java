package com.microsservices.country.models;

import com.microsservices.country.dtos.CountryDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name="countrys")
@Getter
@Setter
public class Country{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private byte[] flag;
    public Country() {
    }
    public Country(CountryDto c) {
        this.name = c.getName();
        this.flag = c.getFlag();
    }

    
}