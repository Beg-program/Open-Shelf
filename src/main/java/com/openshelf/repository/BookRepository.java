package com.openshelf.repository;

import com.openshelf.model.Book;
import com.openshelf.model.Book.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByStatus(Status status);
}
