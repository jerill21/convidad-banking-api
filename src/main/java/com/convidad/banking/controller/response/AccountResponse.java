package com.convidad.banking.controller.response;

public final class AccountResponse {

	private final String accountNumber;
	
	private final String userId;
	
	private final double balance;
	
	public AccountResponse(String accountNumber, String userId, double balance) {
		this.accountNumber = accountNumber;
		this.userId = userId;
		this.balance = balance;
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
}
