package com.emt.lab.service;

import com.emt.lab.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> listAllAuthors();
    List<Author> listAuthorsByName(String name);
    List<Author> listAuthorsBySurname(String surname);
    List<Author> listAuthorsByCountry(Long id);
}
