package com.emt.lab.service;

import com.emt.lab.model.Country;

import java.util.List;

public interface CountryService {
    List<Country> listAllCountries();
    List<Country> listCountriesByContinent(String continent);
}
