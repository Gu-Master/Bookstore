package com.example.library.borrowing;

import com.example.library.book.Book;
import com.example.library.client.Client;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "borrowings", indexes = {
        @Index(name = "idx_borrowings_client", columnList = "client_id"),
        @Index(name = "idx_borrowings_book", columnList = "book_id"),
        @Index(name = "idx_borrowings_borrowed_at", columnList = "borrowed_at")
})
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "borrowed_at", nullable = false)
    private LocalDate borrowedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getBorrowedAt() {
        return borrowedAt;
    }

    public void setBorrowedAt(LocalDate borrowedAt) {
        this.borrowedAt = borrowedAt;
    }
}
