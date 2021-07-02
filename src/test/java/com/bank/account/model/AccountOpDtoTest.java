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

        Account account = new Account(1000d);
        account.setClient(client);

        accountOp = new AccountOp();
        accountOp.setAccount(account);
        accountOp.setOperationType(OperationType.DEPOSIT);
        accountOp.setAmount(100d);

        accountOpDto = new AccountOpDto();
        accountOpDto.setClient("Martin Durant");
        accountOpDto.setType(OperationType.WITHDRAWAL);
        accountOpDto.setAmount(-100d);
        accountOpDto.setDate(LocalDateTime.now());
    }

    @Test
    public void mapAccountOpEntityToAccountOpDto() {
        AccountOpDto dto = modelMapper.map(accountOp, AccountOpDto.class);
        assertEquals(accountOp.getOperationType(), dto.getType());
        assertEquals(accountOp.getAmount(), dto.getAmount());
    }

    @Test
    public void mapAccountOpDtoToAccountOpEntity() {
        AccountOp op = modelMapper.map(accountOpDto, AccountOp.class);
        assertEquals(op.getOperationType(), accountOpDto.getType());
        assertEquals(op.getAmount(), accountOpDto.getAmount());
    }

}