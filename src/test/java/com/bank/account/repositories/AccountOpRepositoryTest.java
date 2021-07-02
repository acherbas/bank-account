package com.bank.account.repositories;

import com.bank.account.model.Account;
import com.bank.account.model.Client;
import com.bank.account.model.AccountOp;
import com.bank.account.model.OperationType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
class AccountOpRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountOpRepository accountOpRepository;

    private static Client client;
    private static Account account;
    private static AccountOp accountOp;


    @BeforeEach
    void setUp() {
        client = new Client(1, "Martin Durant");

        account = new Account();
        account.setId(1);
        account.setClient(client);
        account.setBalance(500d);

        accountOp = new AccountOp(OperationType.DEPOSIT, 100d);
    }

    @Test
    public void saveClientAndFindById() {
        Client savedClient = this.clientRepository.save(client);
        account.setClient(savedClient);
        Account savedBankAccount = this.accountRepository.save(account);
        assertEquals(savedBankAccount.getBalance(), 500d);

        accountOp.setAccount(savedBankAccount);
        AccountOp savedAccountOp = this.accountOpRepository.save(accountOp);
        assertEquals(savedAccountOp.getOperationType(), OperationType.DEPOSIT);
        assertEquals(savedAccountOp.getAmount(), 100d);
    }
}