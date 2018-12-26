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
	
	public AccountBuilder create() {
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

	public static class AccountBuilder {

		private String accountNumber;

		private String userId;

		private double balance;
		
		private Audit audit;
		
		public AccountBuilder() {
			this.audit = new Audit();
			this.accountNumber = UUID.randomUUID().toString();
		}
		
		public AccountBuilder setAccountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
			return this;
		}
		
		public AccountBuilder setUserId(String userId) {
			this.userId = userId;
			return this;
		}

		public AccountBuilder setBalance(double balance) {
			this.balance = balance;
			return this;
		}

		public Account build() {
			return new Account(this);
		}
	}
}
