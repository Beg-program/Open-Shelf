package com.openshelf.service;

import com.openshelf.model.Book;
import com.openshelf.model.Book.Status;
import com.openshelf.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Get all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Get books by status
    public List<Book> getBooksByStatus(Status status) {
        return bookRepository.findByStatus(status);
    }

    // Get only APPROVED books
    public List<Book> getApprovedBooks() {
        return bookRepository.findByStatus(Status.APPROVED);
    }

    // Get a single book by ID
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    // Add a new book (set status to PENDING)
    public Book addBook(Book book) {
        book.setStatus(Status.PENDING);
        return bookRepository.save(book);
    }

    // Update an existing book
    public Book updateBook(Long id, Book updatedBook) {
        return bookRepository.findById(id)
            .map(book -> {
                book.setTitle(updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor());
                book.setDescription(updatedBook.getDescription());
                book.setFileURL(updatedBook.getFileURL());

                // Ensure status is only updated if provided
                if (updatedBook.getStatus() != null) {
                    book.setStatus(updatedBook.getStatus());
                }
                return bookRepository.save(book);
            })
            .orElseGet(() -> {
                updatedBook.setId(id);
                return bookRepository.save(updatedBook);
            });
    }

    // Delete a book
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    // Approve a book
    public Book approveBook(Long id) {
        return bookRepository.findById(id).map(book -> {
            book.setStatus(Status.APPROVED);
            return bookRepository.save(book);
        }).orElse(null);
    }

    // Reject a book
    public Book rejectBook(Long id) {
        return bookRepository.findById(id).map(book -> {
            book.setStatus(Status.REJECTED);
            return bookRepository.save(book);
        }).orElse(null);
    }
}
