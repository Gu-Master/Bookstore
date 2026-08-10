package com.example.library.client;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Page<Client> findAllByOrderByFullNameAsc(Pageable pageable);

    List<Client> findAllByOrderByFullNameAsc();
}
