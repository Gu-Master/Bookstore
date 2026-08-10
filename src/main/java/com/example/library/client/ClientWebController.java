package com.example.library.client;

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
@RequestMapping("/clients")
public class ClientWebController {

    private final ClientService clientService;

    public ClientWebController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String list(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<Client> clients = clientService.findPage(page, 20);
        model.addAttribute("clients", clients);
        return "clients/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("clientForm", new ClientForm());
        fillFormModel(model, "Добавление клиента", "Создать", "/clients");
        return "clients/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("clientForm") ClientForm clientForm,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            fillFormModel(model, "Добавление клиента", "Создать", "/clients");
            return "clients/form";
        }

        clientService.create(clientForm);
        return "redirect:/clients";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Client client = clientService.findById(id);
        ClientForm form = new ClientForm();
        form.setFullName(client.getFullName());
        form.setBirthDate(client.getBirthDate());
        model.addAttribute("clientForm", form);
        fillFormModel(model, "Редактирование клиента", "Сохранить", "/clients/" + id);
        return "clients/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("clientForm") ClientForm clientForm,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            fillFormModel(model, "Редактирование клиента", "Сохранить", "/clients/" + id);
            return "clients/form";
        }

        clientService.update(id, clientForm);
        return "redirect:/clients";
    }

    private void fillFormModel(Model model, String pageTitle, String submitLabel, String formAction) {
        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("submitLabel", submitLabel);
        model.addAttribute("formAction", formAction);
    }
}
