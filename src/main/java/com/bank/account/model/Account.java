package com.bank.account.model;

//import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private Double balance;

	@Column(name = "creation_date")
	private LocalDate creationDate;

	@NotNull(message = "Client is mandatory")
	@OneToOne
	@JoinColumn(name = "client_id")
	private Client client;

	public Account() {
		this.creationDate = LocalDate.now();
		this.balance = 0d;
	}

	public Account(Double balance) {
		this.balance = balance;
		this.creationDate = LocalDate.now();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

}
