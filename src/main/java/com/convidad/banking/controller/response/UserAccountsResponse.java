package com.convidad.banking.controller.response;

import java.util.List;

public final class UserAccountsResponse {

	private final String userId;
	private final List<AccountResponse> accountResponse;
	
	public UserAccountsResponse(String userId, List<AccountResponse> accountResponse) {
		this.userId = userId;
		this.accountResponse = accountResponse;
	}

	public String getUserId() {
		return userId;
	}

	public List<AccountResponse> getAccountResponse() {
		return accountResponse;
	}
}

