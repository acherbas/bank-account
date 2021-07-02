package com.bank.account.controllers;

import com.bank.account.model.AccountOpDto;
import com.bank.account.exception.AccountException;
import com.bank.account.exception.AccountTransactionException;
import com.bank.account.model.OperationType;
import com.bank.account.services.BankAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BankAccountController.class)
class BankAccountControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BankAccountService bankAccountService;

    @BeforeEach
    void setUp() {
        try {
            AccountOpDto deposit = new AccountOpDto();
            deposit.setClient("Martin Durant");
            deposit.setBalance(100d);
            deposit.setType(OperationType.DEPOSIT);
            deposit.setAmount(100d);
            deposit.setDate(LocalDateTime.now());
            when(bankAccountService.deposit(1,100d))
                    .thenReturn(deposit);

            AccountOpDto withdraw = new AccountOpDto();
            withdraw.setClient("Martin Durant");
            withdraw.setBalance(0d);
            withdraw.setType(OperationType.WITHDRAWAL);
            withdraw.setAmount(100d);
            withdraw.setDate(LocalDateTime.now());
            when(bankAccountService.withdraw(1,100d))
                    .thenReturn(withdraw);
        } catch (AccountException | AccountTransactionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getOperationsHistory() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/Account/history/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deposit() throws Exception {
        String accountOpJson = "{\"id\":1, \"amount\":100}";
        mvc.perform(MockMvcRequestBuilders
                .post("/Account/deposit")
                .content(accountOpJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(100));
    }

    @Test
    public void withdraw() throws Exception {
        String accountOpJson = "{\"id\":1, \"amount\":100}";
        mvc.perform(MockMvcRequestBuilders
                .post("/Account/withdraw")
                .content(accountOpJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(0));
    }

}