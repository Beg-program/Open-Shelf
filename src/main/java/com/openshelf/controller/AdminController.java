package com.openshelf.controller;

import com.openshelf.model.Book;
import com.openshelf.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private BookService bookService;

    // GET /admin/pending-books - Show books with PENDING status
    @GetMapping("/pending-books")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Book> getPendingBooks() {
        return bookService.getBooksByStatus(Book.Status.PENDING);
    }

    // PUT /admin/approve/{bookId} - Approve a book
    @PutMapping("/approve/{bookId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> approveBook(@PathVariable Long bookId) {
        Book approvedBook = bookService.approveBook(bookId);
        if (approvedBook != null) {
            return ResponseEntity.ok(approvedBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // PUT /admin/reject/{bookId} - Reject a book
    @PutMapping("/reject/{bookId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> rejectBook(@PathVariable Long bookId) {
        Book rejectedBook = bookService.rejectBook(bookId);
        if (rejectedBook != null) {
            return ResponseEntity.ok(rejectedBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
