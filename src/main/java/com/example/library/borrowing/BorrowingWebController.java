package com.example.library.borrowing;

import com.example.library.book.BookService;
import com.example.library.client.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("/borrowings")
public class BorrowingWebController {

    private final BorrowingService borrowingService;
    private final ClientService clientService;
    private final BookService bookService;

    public BorrowingWebController(BorrowingService borrowingService,
                                  ClientService clientService,
                                  BookService bookService) {
        this.borrowingService = borrowingService;
        this.clientService = clientService;
        this.bookService = bookService;
    }

    @GetMapping
    public String list(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<Borrowing> borrowings = borrowingService.findPage(page, 20);
        model.addAttribute("borrowings", borrowings);
        return "borrowings/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        BorrowingForm form = new BorrowingForm();
        form.setBorrowedAt(LocalDate.now());
        model.addAttribute("borrowingForm", form);
        fillFormModel(model);
        return "borrowings/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("borrowingForm") BorrowingForm borrowingForm,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            fillFormModel(model);
            return "borrowings/form";
        }

        borrowingService.create(borrowingForm);
        return "redirect:/borrowings";
    }

    private void fillFormModel(Model model) {
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("books", bookService.findAll());
    }
}
