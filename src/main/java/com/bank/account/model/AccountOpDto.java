package com.bank.account.model;

import com.bank.account.model.OperationType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class AccountOpDto {

    private int id;
    private OperationType type;
    private Double amount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    @JsonProperty("balance")
    private Double balance;

    @JsonProperty("client")
    private String client;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public OperationType getType() {
        return type;
    }
    public void setType(OperationType type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getBalance() {
        return balance;
    }
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getClient() { return client; }
    public void setClient(String client) { this.client = client; }
}
