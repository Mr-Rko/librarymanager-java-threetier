package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.model.BookStatus;
import com.example.library.model.BorrowRecord;
import com.example.library.model.BorrowStatus;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowService {

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    public BorrowRecord borrowBook(Long bookId, String borrowerName, String borrowerEmail, int borrowDays) {
        Optional<Book> bookOpt = bookRepository.findById(bookId);
        if (bookOpt.isPresent() && bookOpt.get().getStatus() == BookStatus.AVAILABLE) {
            Book book = bookOpt.get();
            book.setStatus(BookStatus.BORROWED);
            bookRepository.save(book);

            LocalDate borrowDate = LocalDate.now();
            LocalDate dueDate = borrowDate.plusDays(borrowDays);

            BorrowRecord record = new BorrowRecord(book, borrowerName, borrowerEmail, borrowDate, dueDate);
            return borrowRecordRepository.save(record);
        }
        return null;
    }

    public boolean returnBook(Long recordId) {
        Optional<BorrowRecord> recordOpt = borrowRecordRepository.findById(recordId);
        if (recordOpt.isPresent()) {
            BorrowRecord record = recordOpt.get();
            record.setReturnDate(LocalDate.now());
            record.setStatus(BorrowStatus.RETURNED);

            Book book = record.getBook();
            book.setStatus(BookStatus.AVAILABLE);
            bookRepository.save(book);

            borrowRecordRepository.save(record);
            return true;
        }
        return false;
    }

    public List<BorrowRecord> getBorrowingHistory() {
        return borrowRecordRepository.findAll();
    }

    public List<BorrowRecord> getCurrentBorrows() {
        return borrowRecordRepository.findByStatus(BorrowStatus.BORROWED);
    }

    public List<BorrowRecord> getOverdueBooks() {
        return borrowRecordRepository.findOverdueRecords(LocalDate.now());
    }

    public Optional<BorrowRecord> getBorrowRecordById(Long id) {
        return borrowRecordRepository.findById(id);
    }
}