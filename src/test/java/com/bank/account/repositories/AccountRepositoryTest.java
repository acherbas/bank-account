package com.bank.account.repositories;

import com.bank.account.model.Account;
import com.bank.account.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;

    private static Client client;
    private static Account account;


    @BeforeEach
    void setUp() {
        client = new Client(1, "Martin Durant");

        account = new Account();
        account.setBalance(500d);
    }

    @Test
    public void saveClientAndFindById() {
        Client savedClient = this.clientRepository.save(client);
        account.setClient(savedClient);
        Account savedAccount = this.accountRepository.save(account);
        assertEquals(savedAccount.getBalance(), 500d);
    }
}