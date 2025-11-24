package com.example.library.controller;

import com.example.library.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/borrow")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @GetMapping
    public String showBorrowForm(@RequestParam Long bookId, Model model) {
        model.addAttribute("bookId", bookId);
        model.addAttribute("borrowDays", 14); // Default borrow period
        return "borrow/form";
    }

    @PostMapping
    public String borrowBook(@RequestParam Long bookId,
                           @RequestParam String borrowerName,
                           @RequestParam String borrowerEmail,
                           @RequestParam int borrowDays) {
        var record = borrowService.borrowBook(bookId, borrowerName, borrowerEmail, borrowDays);
        if (record != null) {
            return "redirect:/books?success=Book borrowed successfully";
        } else {
            return "redirect:/books?error=Book not available";
        }
    }

    @GetMapping("/return/{id}")
    public String returnBook(@PathVariable Long id) {
        if (borrowService.returnBook(id)) {
            return "redirect:/borrow/history?success=Book returned successfully";
        } else {
            return "redirect:/borrow/history?error=Return failed";
        }
    }

    @GetMapping("/history")
    public String showBorrowHistory(Model model) {
        model.addAttribute("borrowRecords", borrowService.getBorrowingHistory());
        model.addAttribute("currentBorrows", borrowService.getCurrentBorrows());
        model.addAttribute("overdueBooks", borrowService.getOverdueBooks());
        return "borrow/history";
    }
}