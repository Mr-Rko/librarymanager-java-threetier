package com.example.library.controller;

import com.example.library.service.BookService;
import com.example.library.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BorrowService borrowService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("totalBooks", bookService.getAllBooks().size());
        model.addAttribute("availableBooks", bookService.getAvailableBooks().size());
        model.addAttribute("currentBorrows", borrowService.getCurrentBorrows().size());
        model.addAttribute("overdueBooks", borrowService.getOverdueBooks().size());
        return "home";
    }
}