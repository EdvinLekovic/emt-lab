package com.emt.lab.service.impl;

import com.emt.lab.model.Author;
import com.emt.lab.model.Book;
import com.emt.lab.model.dto.BookDto;
import com.emt.lab.model.enumeration.Category;
import com.emt.lab.repository.AuthorRepository;
import com.emt.lab.repository.BookRepository;
import com.emt.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book findBookByName(String name) {
        return bookRepository.findBookByName(name);
    }

    @Override
    public List<Book> listBooksByCategory(Category category) {
        return bookRepository.findBooksByCategory(category);
    }

    @Override
    public List<Book> listBooksByAuthor(Long id) {
        Author author = authorRepository.findById(id).get();
        return bookRepository.findBooksByAuthor(author);
    }

    @Override
    public Optional<Book> findBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author = authorRepository.findById(bookDto.getAuthorId()).get();
        return Optional.of(bookRepository.save(new Book(bookDto.getName(),bookDto.getCategory(),author,bookDto.getAvailableCopies())));
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = bookRepository.findById(id).get();
        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        Author author = authorRepository.findById(bookDto.getAuthorId()).get();
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void takeBook(Long id) {
        Book book = bookRepository.findById(id).get();
        if(book.getAvailableCopies()-1==0){
            this.delete(id);
            return;
        }
        book.setAvailableCopies(book.getAvailableCopies()-1);
        bookRepository.save(book);
    }

}
