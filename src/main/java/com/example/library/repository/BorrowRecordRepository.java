package com.example.library.repository;

import com.example.library.model.BorrowRecord;
import com.example.library.model.BorrowStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    List<BorrowRecord> findByBorrowerEmail(String borrowerEmail);
    List<BorrowRecord> findByStatus(BorrowStatus status);
    Optional<BorrowRecord> findByBookIdAndStatus(Long bookId, BorrowStatus status);
    
    @Query("SELECT br FROM BorrowRecord br WHERE br.dueDate < :today AND br.status = 'BORROWED'")
    List<BorrowRecord> findOverdueRecords(LocalDate today);
}