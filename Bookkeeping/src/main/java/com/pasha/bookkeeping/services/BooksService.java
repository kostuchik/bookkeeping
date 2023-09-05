package com.pasha.bookkeeping.services;

import com.pasha.bookkeeping.models.Book;
import com.pasha.bookkeeping.models.Person;
import com.pasha.bookkeeping.repositories.BooksRepository;
import com.pasha.bookkeeping.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BooksService {
    private final BooksRepository booksRepository;
    private final PeopleRepository peopleRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository, PeopleRepository peopleRepository) {
        this.booksRepository = booksRepository;
        this.peopleRepository = peopleRepository;
    }

    public List<Book> index() {
        return booksRepository.findAll();
    }

    public Book show(int id) {
        Optional<Book> foundBook = booksRepository.findById(id);
        return foundBook.orElse(null);
    }

    public void save(Book book) {
        booksRepository.save(book);
    }

    public void update(int id, Book book) {
        book.setBook_id(id);
        booksRepository.save(book);
    }

    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    public void removePerson(int id) {
        Book book = show(id);
        book.setPerson(null);
        booksRepository.save(book);
    }

    public void savePersonId(Book book, int personId) {
        Optional<Person> person = peopleRepository.findById(personId);
        book.setPerson(person.get());
        booksRepository.save(book);
    }
}
