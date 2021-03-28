package com.emt.lab.web;

import com.emt.lab.model.Book;
import com.emt.lab.model.dto.BookDto;
import com.emt.lab.model.enumeration.Category;
import com.emt.lab.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/books")
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBookList(){
        return bookService.listAllBooks();
    }

    @GetMapping("/categories")
    public List<Category> getCategoriesList(){
        return List.of(Category.BIOGRAPHY,
                Category.CLASSICS,
                Category.DRAMA,
                Category.NOVEL,
                Category.FANTASY,
                Category.HISTORY,
                Category.THRILLER);
    }

    @PutMapping("/take/{id}")
    public ResponseEntity takeBook(@PathVariable Long id){
        bookService.takeBook(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Book> findBook(@PathVariable Long id){
        return bookService.findBookById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Book> saveBook(@RequestBody BookDto bookDto){
        return bookService.save(bookDto).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> editBook(@PathVariable Long id,@RequestBody BookDto bookDto){
        return bookService.edit(id,bookDto).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBook(@PathVariable Long id){
        bookService.delete(id);
        if(bookService.findBookById(id).isEmpty()){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
