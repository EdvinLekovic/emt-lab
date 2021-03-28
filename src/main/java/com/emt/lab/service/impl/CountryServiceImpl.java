package com.emt.lab.service.impl;

import com.emt.lab.model.Country;
import com.emt.lab.repository.CountryRepository;
import com.emt.lab.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> listAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public List<Country> listCountriesByContinent(String continent) {
        return countryRepository.findCountriesByContinent(continent);
    }
}
