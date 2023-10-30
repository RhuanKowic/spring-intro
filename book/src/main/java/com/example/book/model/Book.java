package com.example.book.model;

import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "author")
    private String author;

    public Book(){

    }

    public Book(String title, String description, String author){
        this.title = title;
        this.description = description;
        this.author = author;
    }

    public long getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public String getAuthor(){
        return author;
    }
    public void setAuthor(String author){
        this.author = author;
    }

    @Override
    public String toString(){
        return "Book [id=" + id + ", title=" + title + ", description=" + description + ", author=" + author + "]";
    }
}
