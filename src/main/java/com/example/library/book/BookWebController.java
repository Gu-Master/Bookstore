package com.example.library.book;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookWebController {

    private final BookService bookService;

    public BookWebController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String list(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<Book> books = bookService.findPage(page, 20);
        model.addAttribute("books", books);
        return "books/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("bookForm", new BookForm());
        fillFormModel(model, "Добавление книги", "Создать", "/books");
        return "books/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("bookForm") BookForm bookForm,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            fillFormModel(model, "Добавление книги", "Создать", "/books");
            return "books/form";
        }

        try {
            bookService.create(bookForm);
        } catch (IllegalArgumentException ex) {
            bindingResult.rejectValue("isbn", "duplicate", ex.getMessage());
            fillFormModel(model, "Добавление книги", "Создать", "/books");
            return "books/form";
        }

        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id);
        BookForm form = new BookForm();
        form.setTitle(book.getTitle());
        form.setAuthor(book.getAuthor());
        form.setIsbn(book.getIsbn());
        model.addAttribute("bookForm", form);
        fillFormModel(model, "Редактирование книги", "Сохранить", "/books/" + id);
        return "books/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("bookForm") BookForm bookForm,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            fillFormModel(model, "Редактирование книги", "Сохранить", "/books/" + id);
            return "books/form";
        }

        try {
            bookService.update(id, bookForm);
        } catch (IllegalArgumentException ex) {
            bindingResult.rejectValue("isbn", "duplicate", ex.getMessage());
            fillFormModel(model, "Редактирование книги", "Сохранить", "/books/" + id);
            return "books/form";
        }

        return "redirect:/books";
    }

    private void fillFormModel(Model model, String pageTitle, String submitLabel, String formAction) {
        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("submitLabel", submitLabel);
        model.addAttribute("formAction", formAction);
    }
}
