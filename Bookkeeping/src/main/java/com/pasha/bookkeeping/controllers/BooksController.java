package com.pasha.bookkeeping.controllers;

import com.pasha.bookkeeping.dao.BookDAO;
import com.pasha.bookkeeping.dao.PersonDAO;
import com.pasha.bookkeeping.models.Book;
import com.pasha.bookkeeping.services.BooksService;
import com.pasha.bookkeeping.services.PeopleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;
    private final PeopleService peopleService;

    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }


    @GetMapping()
    public String index(Model model,
                        @RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "books_per_page", required = false) Integer bookPerPage,
                        @RequestParam(value = "sort_by_year", required = false) Boolean sortByYear) {

        model.addAttribute("books", booksService.sortAndPagination(page, bookPerPage, sortByYear));
        return "books/index";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("books", booksService.show(id));
        model.addAttribute("person", peopleService.getPersonByBookId(id));
        model.addAttribute("people", peopleService.index());
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String createBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/new";
        booksService.save(book);
        return "redirect:/books";
    }

    @PostMapping("/{id}")
    public String savePersonId(@PathVariable("id") int id,
                               @RequestParam("person_id") int person_id) {
        Book book = booksService.show(id);
        booksService.savePersonId(book, person_id);
        return "redirect:/books";
    }


    @GetMapping("/{id}/edit")
    public String editBook(Model model,
                           @PathVariable("id") int id) {
        model.addAttribute("book", booksService.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult,
                             @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "books/edit";
        booksService.update(id, book);
        return "redirect:/books";
    }

    @PutMapping("/{id}")
    public String removePerson(@PathVariable("id") int id) {
        booksService.removePerson(id);
        return "redirect:/books/{id}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }
}
