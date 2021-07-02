package com.bank.account.model;

import com.bank.account.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class AccountOpDtoTest {
    private ModelMapper modelMapper = new ModelMapper();

    private static AccountOp accountOp;
    private static AccountOpDto accountOpDto;

    @BeforeAll
    public static void setUp() {
        Client client = new Client(1, "Martin Durant");

        Account account = new Account();
        account.setClient(client);
        account.setBalance(1000d);

        accountOp = new AccountOp();
        accountOp.setAccount(account);
        accountOp.setOperationType(OperationType.DEPOSIT);
        accountOp.setAmount(100d);

        accountOpDto = new AccountOpDto();
        accountOpDto.setClient("Martin Durant");
        //accountOpDto.setBalance(500d);
        accountOpDto.setType(OperationType.WITHDRAWAL);
        accountOpDto.setAmount(-100d);
        accountOpDto.setDate(LocalDateTime.now());
    }

    @Test
    public void mapAccountOpEntityToAccountOpDto() {
        AccountOpDto dto = modelMapper.map(accountOp, AccountOpDto.class);
        assertEquals(accountOp.getOperationType(), dto.getType());
        assertEquals(accountOp.getAmount(), dto.getAmount());
        //assertEquals(accountOp.getAccount().getBalance(), dto.getBalance());
        //assertEquals(accountOp.getAccount().getClient().getFullName(), dto.getClient());
    }

    @Test
    public void mapAccountOpDtoToAccountOpEntity() {
        AccountOp op = modelMapper.map(accountOpDto, AccountOp.class);
        assertEquals(op.getOperationType(), accountOpDto.getType());
        assertEquals(op.getAmount(), accountOpDto.getAmount());
        //assertEquals(op.getAccount().getBalance(), accountOpDto.getBalance());
        //assertEquals(op.getAccount().getClient().getFullName(), accountOpDto.getClient());
    }

}