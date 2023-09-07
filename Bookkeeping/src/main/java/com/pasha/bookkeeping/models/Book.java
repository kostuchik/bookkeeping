package com.pasha.bookkeeping.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int book_id;
    @NotEmpty(message = "Title should not be empty")
    @Size(min = 2, max = 100, message = ("Title should be between 2 and 100 characters"))
    @Column(name = "title")
    private String title;
    @NotEmpty(message = "Author should not be empty")
    @Size(min = 2, max = 100, message = ("Author should be between 2 and 100 characters"))
    @Column(name = "author")
    private String author;
    @Min(value = 1901, message = "Year should be greater than 1900")
    @Column(name = "year")
    private int year;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person person;
    @Column(name = "date_of_taken")
    @Temporal(TemporalType.DATE)
    private Date dateOfTaken;
    @Transient
    private boolean expired = false;

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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


    public Date getDateOfTaken() {
        return dateOfTaken;
    }

    public void setDateOfTaken(Date dateOfTaken) {
        this.dateOfTaken = dateOfTaken;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
