package com.bank.account.services;

import com.bank.account.model.AccountOpDto;
import com.bank.account.exception.AccountException;
import com.bank.account.exception.AccountTransactionException;
import com.bank.account.model.Account;
import com.bank.account.model.AccountOp;
import com.bank.account.repositories.AccountRepository;
import com.bank.account.repositories.AccountOpRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class BankAccountServiceTest {

    @Autowired
    BankAccountService bankAccountService;

    @MockBean
    AccountOpRepository accountOpRepository;

    @MockBean
    AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {

        Account account = new Account();
        account.setBalance(1000d);

        AccountOp accountOp = new AccountOp();
        accountOp.setAccount(account);

        when(accountRepository.findById(anyInt()))
                .thenReturn(account);

        when(accountRepository.save(any(Account.class)))
                .thenReturn(account);

        when(accountOpRepository.save(any(AccountOp.class)))
                .thenReturn(accountOp);

    }

    @Test
    void deposit() {
        AccountOpDto deposit = null;
        try {
            deposit = bankAccountService.deposit(1,100d);
            assertNotNull(deposit);
            //assertEquals(deposit.getBalance(), 1100d);
        } catch (AccountException e) {
            e.printStackTrace();
        }
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Test
    void withdraw() {
        AccountOpDto withdraw = null;
        try {
            withdraw = bankAccountService.withdraw(1,100d);
            assertNotNull(withdraw);
            //assertEquals(withdraw.getAmount(), 900d);
        } catch (AccountTransactionException e) {
            e.printStackTrace();
        }
    }
}