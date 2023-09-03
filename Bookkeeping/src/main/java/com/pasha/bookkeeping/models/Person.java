package com.pasha.bookkeeping.models;


import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    private int person_id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<Book> books;

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
    public void addBookToPerson(Book book){
        if(this.books == null)
            this.books = new ArrayList<>();
        this.books.add(book);
        book.setPerson(this);
    }
}
