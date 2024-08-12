
package com.microsservices.country.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microsservices.country.models.Country;

public interface CountryRespository  extends JpaRepository<Country, Long>{

    
}