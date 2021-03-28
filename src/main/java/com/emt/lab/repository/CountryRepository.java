package com.emt.lab.repository;

import com.emt.lab.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country,Long> {
    List<Country> findCountriesByContinent(String continent);
}
