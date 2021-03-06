package com.convidad.banking.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Accounts")
@TypeAlias("account")
public class Account {

	@Id
	@Field("accountNumber")
	private String accountNumber;

	@Field("userId")
	private String userId;

	@Field("balance")
	private double balance;

	@Field("audit")
	private Audit audit;

	private Account(AccountBuilder builder) {
		this.accountNumber = builder.accountNumber;
		this.userId = builder.userId;
		this.balance = builder.balance;
		this.audit = builder.audit;
	}

	public Account() {
	}

	public static AccountBuilder create() {
		return new AccountBuilder();
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getUserId() {
		return userId;
	}

	public double getBalance() {
		return balance;
	}

	public void deposit(double deposit) {
		this.balance += deposit;
	}

	public void withdraw(double amount) {
		this.balance -= amount;
	}

	public Audit getAudit() {
		return audit;
	}

	public static class AccountBuilder {

		private String accountNumber;

		private String userId;

		private double balance;

		private Audit audit;

		public AccountBuilder() {
			this.audit = new Audit();
			this.accountNumber = UUID.randomUUID().toString();
			this.balance = 0d;
		}

		public AccountBuilder setUserId(String userId) {
			this.userId = userId;
			return this;
		}

		public Account build() {
			return new Account(this);
		}
	}
}
