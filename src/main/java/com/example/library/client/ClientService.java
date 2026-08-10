package com.example.library.client;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Page<Client> findPage(int page, int size) {
        return clientRepository.findAllByOrderByFullNameAsc(PageRequest.of(page, size));
    }

    public List<Client> findAll() {
        return clientRepository.findAllByOrderByFullNameAsc();
    }

    public Client findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Клиент не найден: " + id));
    }

    @Transactional
    public Client create(ClientForm form) {
        Client client = new Client();
        apply(client, form);
        return clientRepository.save(client);
    }

    @Transactional
    public Client update(Long id, ClientForm form) {
        Client client = findById(id);
        apply(client, form);
        return clientRepository.save(client);
    }

    private void apply(Client client, ClientForm form) {
        client.setFullName(form.getFullName().trim());
        client.setBirthDate(form.getBirthDate());
    }
}
