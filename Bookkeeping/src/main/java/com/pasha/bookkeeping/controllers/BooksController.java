package com.pasha.bookkeeping.controllers;

import com.pasha.bookkeeping.dao.BookDAO;
import com.pasha.bookkeeping.dao.PersonDAO;
import com.pasha.bookkeeping.models.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("books", bookDAO.showId(id));
        model.addAttribute("person", personDAO.getPersonByBookId(id));
        model.addAttribute("people", personDAO.index());

        return "books/show";
    }


    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String saveBook(@ModelAttribute("book") Book book) {

        bookDAO.save(book);

        return "redirect:/books";
    }

    @PostMapping("/{id}")
    public String savePersonId(@PathVariable("id") int id,
                               @RequestParam("person_id") int person_id) {
        Book book = bookDAO.showId(id);
        bookDAO.savePersonId(book, person_id);

        return "redirect:/books";
    }


    @GetMapping("/{id}/edit")
    public String editBook(Model model,
                           @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.showId(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") Book book,
                             @PathVariable("id") int id) {
        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @PutMapping("/{id}")
    public String PeBook(@PathVariable("id") int id) {
        bookDAO.deletePerson(id);
        return "redirect:/books/{id}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);

        return "redirect:/books";
    }

}
