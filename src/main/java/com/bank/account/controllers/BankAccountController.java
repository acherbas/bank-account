package com.bank.account.controllers;

import com.bank.account.model.AccountOpDto;
import com.bank.account.exception.AccountException;
import com.bank.account.exception.AccountTransactionException;
import com.bank.account.services.BankAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * The Account REST Controller
 *
 * @author MNASRI Saber
 */
@Api( tags = "Rest API for Bank Account")
@RestController
@Validated
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    /**
     * make a deposit in my account
     *
     * @param id int account Id
     * @param amount Double client amount to withdraw
     * @return AccountOpDto client account operation info
     */
    @ApiOperation(value = "US1 : make a deposit in my account")
    @PostMapping(value = "/Account/deposit")
    public ResponseEntity<AccountOpDto> deposit(@RequestBody int id, @RequestBody Double amount) {
        AccountOpDto newOperation = null;
        try {
            newOperation = bankAccountService.deposit(id,amount);
        } catch (AccountException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        if(newOperation == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newOperation.getId())
                .toUri();
        return ResponseEntity.created(location).body(newOperation);
    }

    /**
     * make a withdrawal from my account
     *
     * @param id int account Id
     * @param amount Double client amount to withdraw
     * @return AccountOpDto client account operation info
     */
    @ApiOperation(value = "US2: make a withdrawal from my account")
    @PostMapping(value = "/Account/withdraw")
    public ResponseEntity<AccountOpDto> withdraw(@RequestBody int id, @RequestBody Double amount) {
        AccountOpDto newOperation = null;
        try {
            newOperation = bankAccountService.withdraw(id,amount);
        } catch (AccountTransactionException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        if(newOperation == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newOperation.getId())
                .toUri();
        return ResponseEntity.created(location).body(newOperation);
    }

    /**
     *  see the history (operation, date, amount, balance) of my operations
     *
     * @param id int account Id
     * @return List<AccountOpDto>  bank account operations history
     */
    @ApiOperation(value = "US3: see the history (operation, date, amount, balance) of my operations")
    @GetMapping(value="/Account/history/{id}")
    public ResponseEntity<List<AccountOpDto>> getOperationsHistory(@PathVariable("id") int id) {
        List<AccountOpDto> history = null;
        try {
            history = bankAccountService
                        .getOperationsHistory(id);
        } catch (AccountException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(history, HttpStatus.OK);
    }

    /**
     * Get your bank account balance
     *
     * @param id int account Id
     * @return Double balance
     */
    @ApiOperation(value = "US3: Get your bank account balance")
    @GetMapping(value="/Account/balance/{id}")
    public ResponseEntity<Double> getBalance(@PathVariable("id") int id) {
        Double balance = null;
        try {
            balance = bankAccountService.getBalance(id);
        } catch (AccountException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }

}