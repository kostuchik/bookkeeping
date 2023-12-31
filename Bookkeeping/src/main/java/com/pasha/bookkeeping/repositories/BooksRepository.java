package com.pasha.bookkeeping.repositories;

import com.pasha.bookkeeping.models.Book;
import com.pasha.bookkeeping.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    List<Book> findAllByPerson(Person person);

    List<Book> findByTitleStartingWith(String search);
}
