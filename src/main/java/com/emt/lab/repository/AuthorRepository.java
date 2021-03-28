package com.emt.lab.repository;

import com.emt.lab.model.Author;
import com.emt.lab.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author,Long> {
    List<Author> findAuthorsByName(String name);
    List<Author> findAuthorsBySurname(String surname);
    List<Author> findAuthorsByCountry(Country country);
}
