package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.BookService;
import com.example.library.service.BorrowService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BorrowService borrowService;

    @GetMapping
    public String listBooks(Model model, @RequestParam(required = false) String search) {
        if (search != null && !search.trim().isEmpty()) {
            model.addAttribute("books", bookService.searchBooks(search));
            model.addAttribute("searchTerm", search);
        } else {
            model.addAttribute("books", bookService.getAllBooks());
        }
        model.addAttribute("currentBorrows", borrowService.getCurrentBorrows());
        return "books/list";
    }

    @GetMapping("/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "books/add";
    }

    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "books/add";
        }

        if (!bookService.isIsbnUnique(book.getIsbn())) {
            model.addAttribute("error", "ISBN already exists");
            return "books/add";
        }

        bookService.saveBook(book);
        return "redirect:/books?success=Book added successfully";
    }

    @GetMapping("/edit/{id}")
    public String showEditBookForm(@PathVariable Long id, Model model) {
        Optional<Book> book = bookService.getBookById(id);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            return "books/edit";
        }
        return "redirect:/books?error=Book not found";
    }

    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable Long id, @Valid @ModelAttribute Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "books/edit";
        }
        book.setId(id);
        bookService.saveBook(book);
        return "redirect:/books?success=Book updated successfully";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books?success=Book deleted successfully";
    }

    @GetMapping("/available")
    public String showAvailableBooks(Model model) {
        model.addAttribute("books", bookService.getAvailableBooks());
        return "books/available";
    }
}