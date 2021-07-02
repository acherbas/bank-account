package com.bank.account.services;

import com.bank.account.model.AccountOpDto;
import com.bank.account.exception.AccountException;
import com.bank.account.exception.AccountTransactionException;
import com.bank.account.model.Account;
import com.bank.account.model.AccountOp;
import com.bank.account.model.OperationType;
import com.bank.account.repositories.AccountOpRepository;
import com.bank.account.repositories.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    AccountOpRepository accountOpRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;


    /**
     * make a deposit in my account
     * @param accountId int account id
     * @param amount Double amount to deposit
     * @return AccountOpDto deposit operation
     * @throws AccountException
     */
    @Override
    public AccountOpDto deposit(int accountId, Double amount) throws AccountException {
        AccountOp accountOp = new AccountOp(OperationType.DEPOSIT, amount);
        Account account = accountRepository.findById(accountId);
        if (account == null) {
            throw new AccountException("Account not found " + accountId);
        }
        accountOp.setAccount(account);

        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
        return modelMapper.map(accountOpRepository.save(accountOp), AccountOpDto.class);
    }

    /**
     * make a withdrawal from my account
     * @param accountId
     * @param amount Double amount to withdraw
     * @return AccountOpDto withdrawal operation
     * @throws AccountTransactionException
     */
    @Transactional(propagation = Propagation.MANDATORY )
    @Override
    public AccountOpDto withdraw(int accountId, Double amount) throws AccountTransactionException {
        AccountOp accountOp = new AccountOp(OperationType.WITHDRAWAL, amount);
        Account account = accountRepository.findById(accountId);
        if (account == null) {
            throw new AccountTransactionException("Account not found " + accountId);
        }
        accountOp.setAccount(account);

        amount = amount > 0 ? (-1 * amount) : amount;
        double newBalance = account.getBalance() + amount;
        if (newBalance< 0) {
            throw new AccountTransactionException(
                    "The money in the account '" + accountId + "' is not enough (" + account.getBalance() + ")");
        }
        account.setBalance(newBalance);
        accountRepository.save(account);
        return modelMapper.map(accountOpRepository.save(accountOp), AccountOpDto.class);
    }

    /**
     * get the history (operation, date, amount, balance) of my operations
     * @param accountId int account id
     * @return List<AccountOpDto> list of account operations
     * @throws AccountException
     */
    @Override
    public List<AccountOpDto> getOperationsHistory(int accountId) throws AccountException {
        Account account = accountRepository.findById(accountId);
        if (account == null) {
            throw new AccountException("Account not found " + accountId);
        }
        List<AccountOp> accountOps = accountOpRepository.findAll();
        return accountOps
                .stream()
                .map(operation -> modelMapper.map(operation, AccountOpDto.class))
                .collect(Collectors.toList());
    }

    /**
     * get balance of my account
     * @param accountId int account id
     * @return Double account balance
     * @throws AccountException
     */
    @Override
    public Double getBalance(int accountId) throws AccountException {
        Account account = accountRepository.findById(accountId);
        if (account == null) {
            throw new AccountException("Account not found " + accountId);
        }
        return account.getBalance();
    }
}
