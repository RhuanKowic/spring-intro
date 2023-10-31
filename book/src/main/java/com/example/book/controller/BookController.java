package com.example.book.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.book.model.Book;
import com.example.book.repository.BookRepository;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping
public class BookController {
  @Autowired
  BookRepository bookRepository;

  @GetMapping("/book")
  public ResponseEntity<List<Book>> getAllBook(@RequestParam(required = false) String title) {
    try {
      List<Book> books = bookRepository.findAll();
      if (title != null) {
        books = bookRepository.findByTitleContaining(title);
      }
      return new ResponseEntity<>(books, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/book/{id}")
  public ResponseEntity<Book> getBookById(@PathVariable("id") long id) {
    Optional<Book> bookData = bookRepository.findById(id);

    if (bookData.isPresent()) {
      return new ResponseEntity<>(bookData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/book")
  public ResponseEntity<Book> createBook(@RequestBody Book bookEntity) {
    try {
      Book newBook = bookRepository
          .save(new Book(bookEntity.getTitle(), bookEntity.getDescription(), bookEntity.getAuthor()));
      return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("book/{id}")
  public ResponseEntity<Book> updateBook(@PathVariable("id") long id, @RequestBody Book bookEntity) {
    Optional<Book> bookData = bookRepository.findById(id);
    if (bookData.isPresent()) {
      Book existingBook = bookData.get();
      existingBook.setTitle(bookEntity.getTitle());
      existingBook.setDescription(bookEntity.getDescription());
      existingBook.setAuthor(bookEntity.getAuthor());
      return new ResponseEntity<>(bookRepository.save(existingBook), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/book/{id}")
  public ResponseEntity<Book> deleteBook(@PathVariable("id") long id){
    try{
      bookRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e){
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/book")
  public ResponseEntity<Book> deleteAllBook(){
    try{
      bookRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/book/{author}")
  public ResponseEntity<List<Book>> findByAuthor(@PathVariable("author") String author){
    List<Book> authorBook = bookRepository.findByAuthorContaining(author);
    try{
      if(authorBook.isEmpty()){
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
      }

      return new ResponseEntity<>(authorBook, HttpStatus.OK);
    } catch (Exception e){
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
