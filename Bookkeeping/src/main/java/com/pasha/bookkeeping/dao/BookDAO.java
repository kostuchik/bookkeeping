package com.pasha.bookkeeping.dao;

import com.pasha.bookkeeping.models.Book;
import com.pasha.bookkeeping.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
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
        return session.get(Book.class,id);
    }
    @Transactional
    public void save(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.save(book);
//        jdbcTemplate.update("INSERT INTO book (title, author, year) VALUES (?,?,?)", book.getTitle(), book.getAuthor(), book.getYear());

    }
    @Transactional
    public void update(int id, Book newBook) {
        Session session = sessionFactory.getCurrentSession();
        Book prevBook = session.get(Book.class,id);
        prevBook.setTitle(newBook.getTitle());
        prevBook.setAuthor(newBook.getAuthor());
        prevBook.setYear(newBook.getYear());
        session.update(prevBook);
//        jdbcTemplate.update("UPDATE book  SET title=?, author=?, year=? WHERE book_id=?",
//                book.getTitle(), book.getAuthor(), book.getYear(), id);
    }
    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class,id);
        session.delete(book);

    }
//
//    public void removePerson(int id) {
//        jdbcTemplate.update("UPDATE book  SET person_id=null WHERE book_id=?", id);
//    }
//
//    public void assignPerson(Book book, int personId) {
//        jdbcTemplate.update("UPDATE book  SET person_id=? WHERE book_id=?", personId, book.getBook_id());
//    }
}
