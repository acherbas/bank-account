package com.bank.account.model;

import com.bank.account.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class ClientTest {

    @Autowired
    private TestEntityManager entityManager;

    private Client client;

    @BeforeEach
    public void setUp() {
        client = new Client(1, "Martin Durant");
    }

    @Test
    public void saveClient() {
        Client savedClient = this.entityManager.merge(client);
        assertEquals(savedClient.getFullName(), "Martin Durant");
    }

    @Test
    public void createClientBlankNameException() throws Exception {
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            Client client = new Client(1);
            this.entityManager.merge(client);
        });

        String expectedMessage = "Client full name is mandatory";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}