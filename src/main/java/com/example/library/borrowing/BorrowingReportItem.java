package com.example.library.borrowing;

import java.time.LocalDate;

public class BorrowingReportItem {

    private final String clientFullName;
    private final LocalDate clientBirthDate;
    private final String bookTitle;
    private final String bookAuthor;
    private final String bookIsbn;
    private final LocalDate borrowedAt;

    public BorrowingReportItem(String clientFullName,
                               LocalDate clientBirthDate,
                               String bookTitle,
                               String bookAuthor,
                               String bookIsbn,
                               LocalDate borrowedAt) {
        this.clientFullName = clientFullName;
        this.clientBirthDate = clientBirthDate;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookIsbn = bookIsbn;
        this.borrowedAt = borrowedAt;
    }

    public String getClientFullName() {
        return clientFullName;
    }

    public LocalDate getClientBirthDate() {
        return clientBirthDate;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public LocalDate getBorrowedAt() {
        return borrowedAt;
    }
}
