package com.bank.account.model;

public class AccountInfo {

    private int accountId;
    private Double amount;

    public AccountInfo() {}

    public AccountInfo(int accountId, Double amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    public int getAccountId() {
        return accountId;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public Double getAmount() {
        return amount;
    }

}
