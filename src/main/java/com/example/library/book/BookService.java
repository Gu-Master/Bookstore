package com.example.library.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Page<Book> findPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookRepository.findAllByOrderByTitleAsc(pageable);
    }

    public List<Book> findAll() {
        return bookRepository.findAllByOrderByTitleAsc();
    }

    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Книга не найдена: " + id));
    }

    @Transactional
    public Book create(BookForm form) {
        validateIsbn(form.getIsbn(), null);
        Book book = new Book();
        apply(book, form);
        return bookRepository.save(book);
    }

    @Transactional
    public Book update(Long id, BookForm form) {
        Book book = findById(id);
        validateIsbn(form.getIsbn(), id);
        apply(book, form);
        return bookRepository.save(book);
    }

    private void apply(Book book, BookForm form) {
        book.setTitle(form.getTitle().trim());
        book.setAuthor(form.getAuthor().trim());
        book.setIsbn(form.getIsbn().trim());
    }

    private void validateIsbn(String isbn, Long currentId) {
        boolean alreadyExists = currentId == null
                ? bookRepository.existsByIsbn(isbn)
                : bookRepository.existsByIsbnAndIdNot(isbn, currentId);

        if (alreadyExists) {
            throw new IllegalArgumentException("Книга с таким ISBN уже существует");
        }
    }
}
