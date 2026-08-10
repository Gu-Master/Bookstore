package com.example.library.book;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class BookForm {

    @NotBlank(message = "Укажите наименование книги")
    @Size(max = 255, message = "Наименование не должно превышать 255 символов")
    private String title;

    @NotBlank(message = "Укажите автора")
    @Size(max = 255, message = "Автор не должен превышать 255 символов")
    private String author;

    @NotBlank(message = "Укажите ISBN")
    @Size(max = 20, message = "ISBN не должен превышать 20 символов")
    @Pattern(regexp = "[0-9X\\-]+", message = "ISBN может содержать только цифры, X и дефис")
    private String isbn;

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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
