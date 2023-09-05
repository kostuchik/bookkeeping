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
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final BooksRepository booksRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository, BooksRepository booksRepository) {
        this.peopleRepository = peopleRepository;
        this.booksRepository = booksRepository;
    }

    public List<Person> index() {
        return peopleRepository.findAll();
    }

    public Person show(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }
    public Optional<Person> show(String name) {
        Optional<Person> foundPerson = peopleRepository.findByName(name);
        return foundPerson;
    }

    public void save(Person person) {
        peopleRepository.save(person);
    }

    public void update(int id, Person person) {
        person.setPerson_id(id);
        peopleRepository.save(person);
    }

    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    public List<Book> getBooksByPersonId(int id) {
        return booksRepository.findAllByPerson(show(id));
    }

    public Optional<Person> getPersonByBookId(int id) {
        Optional<Book> book = booksRepository.findById(id);
        Person person = book.get().getPerson();
        return Optional.ofNullable(person);
    }
}
