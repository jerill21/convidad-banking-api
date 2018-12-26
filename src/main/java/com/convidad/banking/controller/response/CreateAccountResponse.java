package com.convidad.banking.controller.response;

public final class CreateAccountResponse {

	private final String accountId;
	
	public CreateAccountResponse(String accountId) {
		this.accountId = accountId;
	}
	
	public String getAccountId() {
		return accountId;
	}
}
