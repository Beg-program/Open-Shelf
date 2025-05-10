package com.openshelf.model;

import jakarta.persistence.*;

@Entity
public class Book {

    public enum Status {
        PENDING,
        APPROVED,
        REJECTED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(length = 1000)
    private String description;

    private String fileURL;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    // Constructors
    public Book() {
        this.status = Status.PENDING;
    }

    public Book(String title, String author, String description, String fileURL, Status status) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.fileURL = fileURL;
        this.status = status != null ? status : Status.PENDING;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileURL() {
        return fileURL;
    }

    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
