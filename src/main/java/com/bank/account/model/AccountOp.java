package com.bank.account.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "account_op")
public class AccountOp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull(message = "Operation type is mandatory")
	@Enumerated(EnumType.STRING)
	@Column(name = "operation_type")
	private OperationType operationType;

	@NotNull(message = "Amount is mandatory")
	private Double amount;

	@Column(name = "operation_date")
	private LocalDateTime operationDate;

	@NotNull(message = "Account is mandatory")
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;

	public AccountOp() {
		this.operationDate = LocalDateTime.now();
	}

	public AccountOp(OperationType operationType, Double amount) {
		this.operationType = operationType;
		this.amount = amount;
		this.operationDate = LocalDateTime.now();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public OperationType getOperationType() {
		return operationType;
	}
	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}

	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDateTime getOperationDate() {
		return operationDate;
	}
	public void setOperationDate(LocalDateTime operationDate) {
		this.operationDate = operationDate;
	}

	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		return "Operation{"+
				"id=" + id +
				", type=" + operationType +
				", amount="+ amount +
				", date=" + operationDate.format(formatter) + "}";
	}
}
