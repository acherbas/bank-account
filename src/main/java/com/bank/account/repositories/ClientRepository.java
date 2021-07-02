package com.bank.account.repositories;

import com.bank.account.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findById(int id);
    List<Client> findByFullName(String name);
}
