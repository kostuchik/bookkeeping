package com.pasha.bookkeeping.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {
    private int book_id;
    @NotEmpty(message = "Title should not be empty")
    @Size(min = 2, max = 100, message = ("Title should be between 2 and 100 characters"))
    private String title;
    @NotEmpty(message = "Author should not be empty")
    @Size(min = 2, max = 100, message = ("Author should be between 2 and 100 characters"))
    private String author;
    @Min(value = 1900, message = "Year should be greater than 1900")
    private int year;

    public Book() {
    }

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
