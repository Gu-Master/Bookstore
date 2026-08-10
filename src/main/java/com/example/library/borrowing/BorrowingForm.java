package com.example.library.borrowing;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

public class BorrowingForm {

    @NotNull(message = "Выберите клиента")
    private Long clientId;

    @NotNull(message = "Выберите книгу")
    private Long bookId;

    @NotNull(message = "Укажите дату выдачи")
    @PastOrPresent(message = "Дата выдачи не может быть в будущем")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate borrowedAt;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public LocalDate getBorrowedAt() {
        return borrowedAt;
    }

    public void setBorrowedAt(LocalDate borrowedAt) {
        this.borrowedAt = borrowedAt;
    }
}
