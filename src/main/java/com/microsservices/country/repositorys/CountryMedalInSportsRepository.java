package com.microsservices.country.repositorys;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.microsservices.country.models.CountryMedalInSports;

public interface CountryMedalInSportsRepository extends JpaRepository<CountryMedalInSports, Long>{

@Query("SELECT csm FROM country_medal_in_sports csm " +
           "JOIN FETCH csm.country country " +
           "JOIN FETCH csm.sport sport " +
           "JOIN FETCH csm.medal medal " +
           "WHERE country.name = :countryName")
    List<CountryMedalInSports> findByCountryName(@Param("countryName") String countryName);

@Query("SELECT csm FROM country_medal_in_sports csm " +
           "JOIN FETCH csm.country country " +
           "JOIN FETCH csm.medal medal")
    List<CountryMedalInSports> findCountriesAndMedals();
}