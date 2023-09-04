package com.pasha.bookkeeping.dao;

import com.pasha.bookkeeping.models.Book;
import com.pasha.bookkeeping.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
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
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }

    @Transactional
    public void update(int id, Person person) {
        Session session = sessionFactory.getCurrentSession();
        Person personUpdate = session.get(Person.class, id);
        personUpdate.setName(person.getName());
        personUpdate.setAge(person.getAge());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        session.delete(person);
    }

    @Transactional
    public List<Book> getBooksByPersonId(int id) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        List<Book> books = session.createQuery("FROM Book WHERE person=:person")
                .setParameter("person", person)
                .getResultList();
        return books;
    }

    @Transactional
    public Optional<Person> getPersonByBookId(int id) {
        Session session = sessionFactory.getCurrentSession();
        Optional<Person> person = session.createQuery("SELECT p FROM Person p JOIN p.bookList b  WHERE  b.book_id=:book_id", Person.class)
                .setParameter("book_id", id)
                .uniqueResultOptional();
        return person;
    }
}
