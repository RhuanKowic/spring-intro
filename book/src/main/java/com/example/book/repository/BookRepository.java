package com.example.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.book.model.Book;
import java.util.List;


public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthorContaining(String author);
    List<Book> findByTitleContaining(String title);
}
