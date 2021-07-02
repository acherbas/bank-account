package com.bank.account.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "client")
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	//@NotBlank(message = "Client full name is mandatory")
	@Column(name = "full_name")
	@NotEmpty(message = "Client full name is mandatory")
	private String fullName;

	@Column(name = "birth_date")
	private LocalDate birthDate;
	private String address;
	private String email;
	private String phone;

	public Client() {}

	public Client(int id) {
		this.id = id;
	}

	public Client(int id, String fullName) {
		this.id = id;
		this.fullName = fullName;
	}

	public int getId() { return id;	}
	public void setId(int id) {	this.id = id;}

	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Client client = (Client) o;
		return id == client.id && fullName.equals(client.fullName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, fullName);
	}
}
