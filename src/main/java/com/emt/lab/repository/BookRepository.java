package com.emt.lab.repository;

import com.emt.lab.model.Author;
import com.emt.lab.model.Book;
import com.emt.lab.model.enumeration.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    Book findBookByName(String name);
    List<Book> findBooksByCategory(Category category);
    List<Book> findBooksByAuthor(Author author);
}
