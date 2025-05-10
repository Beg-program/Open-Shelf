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
    @PreAuthorize("hasRole('ADMIN')")  // Ensuring only ADMIN can access this endpoint
    public List<Book> getPendingBooks() {
        return bookService.getBooksByStatus(Book.Status.PENDING);  // Returns a list of books with PENDING status
    }

    // PUT /admin/approve/{bookId} - Approve a book
    @PutMapping("/approve/{bookId}")
    @PreAuthorize("hasRole('ADMIN')")  // Ensuring only ADMIN can approve
    public ResponseEntity<Book> approveBook(@PathVariable Long bookId) {
        Book approvedBook = bookService.approveBook(bookId);  // Approves the book
        if (approvedBook != null) {
            return ResponseEntity.ok(approvedBook);  // Return the approved book details
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Return 404 if the book wasn't found
        }
    }

    // PUT /admin/reject/{bookId} - Reject a book
    @PutMapping("/reject/{bookId}")
    @PreAuthorize("hasRole('ADMIN')")  // Ensuring only ADMIN can reject
    public ResponseEntity<Book> rejectBook(@PathVariable Long bookId) {
        Book rejectedBook = bookService.rejectBook(bookId);  // Rejects the book
        if (rejectedBook != null) {
            return ResponseEntity.ok(rejectedBook);  // Return the rejected book details
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Return 404 if the book wasn't found
        }
    }
}
