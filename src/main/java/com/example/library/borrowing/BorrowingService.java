package com.example.library.borrowing;

import com.example.library.book.Book;
import com.example.library.book.BookService;
import com.example.library.client.Client;
import com.example.library.client.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BorrowingService {

    private final BorrowingRepository borrowingRepository;
    private final ClientService clientService;
    private final BookService bookService;

    public BorrowingService(BorrowingRepository borrowingRepository,
                            ClientService clientService,
                            BookService bookService) {
        this.borrowingRepository = borrowingRepository;
        this.clientService = clientService;
        this.bookService = bookService;
    }

    public Page<Borrowing> findPage(int page, int size) {
        return borrowingRepository.findAllByOrderByBorrowedAtDescIdDesc(PageRequest.of(page, size));
    }

    public List<BorrowingReportItem> getReport() {
        return borrowingRepository.findAllForReport();
    }

    @Transactional
    public Borrowing create(BorrowingForm form) {
        Client client = clientService.findById(form.getClientId());
        Book book = bookService.findById(form.getBookId());

        Borrowing borrowing = new Borrowing();
        borrowing.setClient(client);
        borrowing.setBook(book);
        borrowing.setBorrowedAt(form.getBorrowedAt());
        return borrowingRepository.save(borrowing);
    }
}
