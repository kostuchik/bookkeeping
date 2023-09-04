package com.pasha.bookkeeping.dao;

import com.pasha.bookkeeping.models.Book;
import com.pasha.bookkeeping.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BookDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public BookDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<Book> index() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Book", Book.class).getResultList();
    }

    @Transactional
    public Book show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Book.class, id);
    }

    @Transactional
    public void save(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.save(book);
    }

    @Transactional
    public void update(int id, Book book) {
        Session session = sessionFactory.getCurrentSession();
        Book bookUpdate = session.get(Book.class, id);
        bookUpdate.setTitle(book.getTitle());
        bookUpdate.setAuthor(book.getAuthor());
        bookUpdate.setYear(book.getYear());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);
        session.delete(book);
    }

    @Transactional
    public void removePerson(int id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);
        book.setPerson(null);
    }

    @Transactional
    public void savePersonId(Book book, int personId) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, personId);
        Book bookUpdate = session.get(Book.class, book.getBook_id());
        bookUpdate.setPerson(person);
    }
}
