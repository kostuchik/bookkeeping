package com.pasha.bookkeeping.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int person_id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    @Pattern(regexp = "^[А-ЯA-Za-z]+[А-Яа-яA-Za-z- ]*$", message = "Name should contain only letters, spaces, and hyphens")
    @Column(name = "name")
    private String name;
    @Min(value = 18, message = "Age should be greater than or equal to 18")
    @Max(value = 120, message = "Age should be less than or equal to 120")
    @Column(name = "age")
    private int age;
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Book> bookList;

    public Person() {
    }

    public Person(String name, int year) {
        this.name = name;
        this.age = year;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public void addBookToPerson(Book book) {
        if (bookList == null)
            bookList = new ArrayList<>();
        bookList.add(book);
        book.setPerson(this);
    }
}
