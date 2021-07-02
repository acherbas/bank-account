package com.bank.account.services;

import com.bank.account.model.AccountInfo;
import com.bank.account.model.AccountOpDto;
import com.bank.account.exception.AccountException;
import com.bank.account.exception.AccountTransactionException;

import java.util.List;

public interface BankAccountService {

    /**
     * make a deposit in my account
     * @param accountInfo account info (accountId, amount)
     * @return AccountOpDto deposit operation
     * @throws AccountException
     */
    AccountOpDto deposit(AccountInfo accountInfo) throws AccountException;

    /**
     * make a withdrawal from my account
     * @param accountInfo account info (accountId, amount)
     * @return AccountOpDto withdrawal operation
     * @throws AccountTransactionException
     */
    AccountOpDto withdraw(AccountInfo accountInfo) throws AccountTransactionException;

    /**
     * get the history (operation, date, amount, balance) of my operations
     * @param accountId int account id
     * @return List<AccountOpDto> list of account operations
     * @throws AccountException
     */
    List<AccountOpDto> getOperationsHistory(int accountId) throws AccountException;

    /**
     * get balance of my account
     * @param accountId int account id
     * @return Double account balance
     * @throws AccountException
     */
    Double getBalance(int accountId) throws AccountException;
}
