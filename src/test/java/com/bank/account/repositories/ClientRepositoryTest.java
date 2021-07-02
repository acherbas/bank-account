package com.bank.account.repositories;

import com.bank.account.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    private static Client client;

    @BeforeEach
    void setUp() {
        client = new Client(1, "Martin Durant");
    }

    @Test
    public void saveClientAndFindById() {
        Client savedClient = this.clientRepository.save(client);
        assertEquals(this.clientRepository.findById(savedClient.getId()), client);
    }

    @Test
    public void saveClientAndFindByFullName() {
        Client savedClient = this.clientRepository.save(client);
        List<Client> clients = this.clientRepository.findByFullName(savedClient.getFullName());
        assertEquals(clients.get(0).getFullName(), "Martin Durant");
    }
}