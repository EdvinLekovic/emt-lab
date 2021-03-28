package com.emt.lab.service;

import com.emt.lab.model.Book;
import com.emt.lab.model.dto.BookDto;
import com.emt.lab.model.enumeration.Category;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listAllBooks();
    Book findBookByName(String name);
    List<Book> listBooksByCategory(Category category);
    List<Book> listBooksByAuthor(Long id);
    Optional<Book> findBookById(Long id);
    Optional<Book> save(BookDto bookDto);
    Optional<Book> edit(Long id,BookDto bookDto);
    void delete(Long id);
    void takeBook(Long id);
}
