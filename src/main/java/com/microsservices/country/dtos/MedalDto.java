package com.microsservices.country.dtos;

import java.util.List;

import com.microsservices.country.models.Medal;
import com.microsservices.country.models.Sport;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter 
public class MedalDto {
    private Medal medal;
    private List<Sport> sports;
}
