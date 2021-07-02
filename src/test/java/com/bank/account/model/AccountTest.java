package com.bank.account.model;

import com.bank.account.model.Account;
import com.bank.account.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@DataJpaTest
class AccountTest {

    @Autowired
    private TestEntityManager entityManager;

    private Account account;
    private Client client;

    @BeforeEach
    public void setUp() {
        client = new Client(1, "Martin Durant");

        account = new Account(500d);
    }

    @Test
    public void saveClient() {
        Client savedClient = this.entityManager.merge(client);
        account.setClient(savedClient);
        Account savedAccount = this.entityManager.merge(account);
        assertEquals(savedAccount.getBalance(), 500d);
    }

    @Test
    public void createClientNullException() throws Exception {
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            this.entityManager.persist(new Account());
        });

        String expectedMessage = "Client is mandatory";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}