package com.example.library;

import com.example.library.book.Book;
import com.example.library.book.BookRepository;
import com.example.library.borrowing.Borrowing;
import com.example.library.borrowing.BorrowingRepository;
import com.example.library.client.Client;
import com.example.library.client.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class LibraryApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BorrowingRepository borrowingRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        borrowingRepository.deleteAll();
        bookRepository.deleteAll();
        clientRepository.deleteAll();

        Client client = new Client();
        client.setFullName("Иван Иванов");
        client.setBirthDate(LocalDate.of(1991, 4, 15));
        client = clientRepository.save(client);

        Book book = new Book();
        book.setTitle("Чистый код");
        book.setAuthor("Роберт Мартин");
        book.setIsbn("978-5-00100-000-1");
        book = bookRepository.save(book);

        Borrowing borrowing = new Borrowing();
        borrowing.setClient(client);
        borrowing.setBook(book);
        borrowing.setBorrowedAt(LocalDate.of(2026, 8, 10));
        borrowingRepository.save(borrowing);
    }

    @Test
    void readersApiReturnsBorrowingReport() throws Exception {
        mockMvc.perform(get("/api/readers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].clientFullName").value("Иван Иванов"))
                .andExpect(jsonPath("$[0].clientBirthDate").value("1991-04-15"))
                .andExpect(jsonPath("$[0].bookTitle").value("Чистый код"))
                .andExpect(jsonPath("$[0].bookAuthor").value("Роберт Мартин"))
                .andExpect(jsonPath("$[0].bookIsbn").value("978-5-00100-000-1"))
                .andExpect(jsonPath("$[0].borrowedAt").value("2026-08-10"));
    }

    @Test
    void booksPageRenders() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Книги")));
    }

    @Test
    void clientsPageRenders() throws Exception {
        mockMvc.perform(get("/clients"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Клиенты")));
    }

    @Test
    void newBorrowingPageRenders() throws Exception {
        mockMvc.perform(get("/borrowings/new"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Выдать книгу")));
    }
}
