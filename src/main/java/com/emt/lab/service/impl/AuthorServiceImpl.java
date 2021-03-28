package com.emt.lab.service.impl;

import com.emt.lab.model.Author;
import com.emt.lab.model.Country;
import com.emt.lab.repository.AuthorRepository;
import com.emt.lab.repository.CountryRepository;
import com.emt.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> listAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public List<Author> listAuthorsByName(String name) {
        return authorRepository.findAuthorsByName(name);
    }

    @Override
    public List<Author> listAuthorsBySurname(String surname) {
        return authorRepository.findAuthorsBySurname(surname);
    }

    @Override
    public List<Author> listAuthorsByCountry(Long id) {
        Country country = countryRepository.findById(id).get();
        return authorRepository.findAuthorsByCountry(country);
    }
}
