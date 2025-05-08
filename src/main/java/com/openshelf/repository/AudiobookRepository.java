package com.openshelf.repository;

import com.openshelf.model.Audiobook;
import com.openshelf.model.Audiobook.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AudiobookRepository extends JpaRepository<Audiobook, Long> {
    List<Audiobook> findByStatus(Status status);
}
