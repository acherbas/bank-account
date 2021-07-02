package com.bank.account.services;

import com.bank.account.model.AccountOpDto;
import com.bank.account.exception.AccountException;
import com.bank.account.exception.AccountTransactionException;

import java.util.List;

public interface BankAccountService {

    AccountOpDto deposit(Integer id, Double amount) throws AccountException;

    AccountOpDto withdraw(Integer id, Double amount) throws AccountTransactionException;

    List<AccountOpDto> getOperationsHistory(Integer id) throws AccountException;

    Double getBalance(Integer id) throws AccountException;
}
