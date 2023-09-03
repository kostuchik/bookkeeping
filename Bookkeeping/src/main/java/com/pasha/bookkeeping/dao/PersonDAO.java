package com.pasha.bookkeeping.dao;

import com.pasha.bookkeeping.models.Book;
import com.pasha.bookkeeping.models.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Person", Person.class).getResultList();
    }

    @Transactional
    public Person show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
    }

    @Transactional
    public Optional<Person> show(String name) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, name);
        Optional<Person> optional = Optional.ofNullable(person);
        return optional;
    }

    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }

    @Transactional
    public void update(int id, Person newPerson) {
        Session session = sessionFactory.getCurrentSession();
        Person prevPerson = session.get(Person.class,id);
        prevPerson.setName(newPerson.getName());
        prevPerson.setAge(newPerson.getAge());
        session.update(prevPerson);

//        jdbcTemplate.update("UPDATE person SET name=?, age=? WHERE person_id=?", person.getName(), person.getAge(), id);
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(Person.class, id));
//        jdbcTemplate.update("DELETE FROM person WHERE person_id=?", id);
    }
//
//    public List<Book> getBooksByPersonId(int id) {
//        List<Book> book = jdbcTemplate.query("SELECT * FROM book WHERE person_id=?"
//                , new Object[]{show(id).getPerson_id()}
//                , new BeanPropertyRowMapper<>(Book.class)).stream().toList();
//        return book;
//    }
//
//    public Optional<Person> getPersonByBookId(int id) {
//        Optional<Person> person = jdbcTemplate.query("SELECT * FROM  person p join book b on p.person_id = b.person_id WHERE book_id=?"
//                , new Object[]{id}
//                , new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
//        return person;
//    }
}
