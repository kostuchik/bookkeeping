package com.pasha.bookkeeping.dao;

import com.pasha.bookkeeping.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book showById(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE book_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book (title, author, year) VALUES (?,?,?)", book.getTitle(), book.getAuthor(), book.getYear());

    }

    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE book  SET title=?, author=?, year=? WHERE book_id=?",
                book.getTitle(), book.getAuthor(), book.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE book_id=?", id);
    }

    public void removePerson(int id) {
        jdbcTemplate.update("UPDATE book  SET person_id=null WHERE book_id=?", id);
    }

    public void savePersonId(Book book, int personId) {
        jdbcTemplate.update("UPDATE book  SET person_id=? WHERE book_id=?", personId, book.getBook_id());
    }
}
