package com.PSproject.controller;

import com.PSproject.dataSave.DataSaveObservable;
import com.PSproject.dataSave.DataSaveObserver;
import com.PSproject.exception.BookNotFoundException;
import com.PSproject.helpers.Utility;
import com.PSproject.model.Book;
import com.PSproject.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class BookController {
    private DataSaveObservable dataSaveObservable = new DataSaveObservable("json");
    private DataSaveObserver dataSaveObserver = new DataSaveObserver(dataSaveObservable);

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/book/{id}")
    Book getBookById(@PathVariable Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    @PostMapping("/book")
    Book newBook(@RequestBody Book newBook) {
        return bookRepository.save(newBook);
    }

    @PutMapping("/book/{id}")
    Book updateBook(@RequestBody Book newBook, @PathVariable Long id) {
        return bookRepository.findById(id).map(book -> {
            book.setTitle(newBook.getTitle());
            book.setAuthor(newBook.getAuthor());
            book.setDomain(newBook.getDomain());
            book.setPrice(newBook.getPrice());
            book.setDisponibility(newBook.getDisponibility());
            book.setNoOfPages(newBook.getNoOfPages());
            book.setPublisher(newBook.getPublisher());
            book.setYear(newBook.getYear());
            book.setIsbn(newBook.getIsbn());
            book.setLanguage(newBook.getLanguage());
            return bookRepository.save(book);
        }).orElseThrow(() -> new BookNotFoundException(id));
    }

    @DeleteMapping("/book/{id}")
    String deleteBook(@PathVariable Long id) {
        if(!bookRepository.existsById(id))
            throw new BookNotFoundException(id);
        bookRepository.deleteById(id);
        return "Book with id " + id + " was successfully deleted";
    }

    @GetMapping("/books/save/{type}")
    String saveBooks(@PathVariable String type) {
        dataSaveObservable.setType(type);
        return "Books saved successfully";
    }

}
