package com.bank.account.model;

import com.bank.account.model.Account;
import com.bank.account.model.Client;
import com.bank.account.model.AccountOp;
import com.bank.account.model.OperationType;
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
class AccountOpTest {

    @Autowired
    private TestEntityManager entityManager;

    private Account account;
    private Client client;
    private AccountOp accountOp;

    @BeforeEach
    public void setUp() {
        client = new Client(1, "Martin Durant");
        account = new Account();
        accountOp = new AccountOp(OperationType.DEPOSIT, 100d);
    }

    @Test
    public void saveAccountOp() {
        Client savedClient = this.entityManager.merge(client);
        account.setClient(savedClient);

        Account savedAccount = this.entityManager.merge(account);
        assertEquals(savedAccount.getBalance(), 0d);

        accountOp.setAccount(savedAccount);
        AccountOp savedAccountOp = this.entityManager.merge(accountOp);
        assertEquals(savedAccountOp.getOperationType(), OperationType.DEPOSIT);
        assertEquals(savedAccountOp.getAmount(), 100d);
    }

    @Test
    public void createAccountNullException() throws Exception {
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            this.entityManager.persist(new AccountOp(OperationType.WITHDRAWAL, -10d));
        });

        String expectedMessage = "Account is mandatory";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void createOperationTypeNullException() throws Exception {
        Client savedClient = this.entityManager.merge(client);
        account.setClient(savedClient);
        Account savedBankAccount = this.entityManager.merge(account);
        assertEquals(savedBankAccount.getBalance(), 0d);

        AccountOp nullTypeAccountOp = new AccountOp();
        nullTypeAccountOp.setAccount(savedBankAccount);
        nullTypeAccountOp.setAmount(100d);

        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            this.entityManager.persist(nullTypeAccountOp);
        });

        String expectedMessage = "Operation type is mandatory";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void createAmountNullException() throws Exception {
        Client savedClient = this.entityManager.merge(client);
        account.setClient(savedClient);
        Account savedBankAccount = this.entityManager.merge(account);
        assertEquals(savedBankAccount.getBalance(), 0d);

        AccountOp nullAmountAccountOp = new AccountOp();
        nullAmountAccountOp.setAccount(savedBankAccount);
        nullAmountAccountOp.setOperationType(OperationType.WITHDRAWAL);

        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            this.entityManager.persist(nullAmountAccountOp);
        });

        String expectedMessage = "Amount is mandatory";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}