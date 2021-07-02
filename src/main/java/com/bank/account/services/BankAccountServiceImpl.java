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


    @Override
    public AccountOpDto deposit(Integer id, Double amount) throws AccountException {
        AccountOp accountOp = new AccountOp(OperationType.DEPOSIT, amount);
        Account account = accountRepository.findById(id);
        if (account == null) {
            throw new AccountException("Account not found " + id);
        }
        accountOp.setAccount(account);

        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
        return modelMapper.map(accountOpRepository.save(accountOp), AccountOpDto.class);
    }

    // MANDATORY: Transaction must be created before.
    @Transactional(propagation = Propagation.MANDATORY )
    @Override
    public AccountOpDto withdraw(Integer id, Double amount) throws AccountTransactionException {
        AccountOp accountOp = new AccountOp(OperationType.WITHDRAWAL, amount);
        Account account = accountRepository.findById(id);
        if (account == null) {
            throw new AccountTransactionException("Account not found " + id);
        }
        accountOp.setAccount(account);

        amount = amount > 0 ? (-1 * amount) : amount;
        double newBalance = account.getBalance() + amount;
        if (newBalance< 0) {
            throw new AccountTransactionException(
                    "The money in the account '" + id + "' is not enough (" + account.getBalance() + ")");
        }
        account.setBalance(newBalance);
        accountRepository.save(account);
        return modelMapper.map(accountOpRepository.save(accountOp), AccountOpDto.class);
    }

    @Override
    public List<AccountOpDto> getOperationsHistory(Integer id) throws AccountException {
        Account account = accountRepository.findById(id);
        if (account == null) {
            throw new AccountException("Account not found " + id);
        }
        List<AccountOp> accountOps = accountOpRepository.findAll();
        return accountOps
                .stream()
                .map(operation -> modelMapper.map(operation, AccountOpDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Double getBalance(Integer id) throws AccountException {
        Account account = accountRepository.findById(id);
        if (account == null) {
            throw new AccountException("Account not found " + id);
        }
        return account.getBalance();
    }
}
