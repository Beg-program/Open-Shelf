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
                book.setStatus(updatedBook.getStatus());
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
}
