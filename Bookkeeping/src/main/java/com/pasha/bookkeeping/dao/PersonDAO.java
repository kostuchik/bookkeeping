package com.pasha.bookkeeping.dao;

import com.pasha.bookkeeping.models.Book;
import com.pasha.bookkeeping.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT*FROM person WHERE person_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }
    public Optional<Person> show(String name){
        return jdbcTemplate.query("SELECT * FROM person WHERE name=?"
                , new Object[]{name}
                , new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person (name, age) VALUES (?,?)", person.getName(), person.getAge());
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE person SET name=?, age=? WHERE person_id=?", person.getName(), person.getAge(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE person_id=?", id);
    }

    public List<Book> getBooksByPersonId(int id) {
        List<Book> book = jdbcTemplate.query("SELECT * FROM book WHERE person_id=?"
                , new Object[]{show(id).getPerson_id()}
                , new BeanPropertyRowMapper<>(Book.class)).stream().toList();
        return book;
    }

    public Optional<Person>  getPersonByBookId(int id) {
        Optional<Person> person =  jdbcTemplate.query("SELECT * FROM  person p join book b on p.person_id = b.person_id WHERE book_id=?"
                , new Object[]{id}
                , new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
        return person;
    }
}
