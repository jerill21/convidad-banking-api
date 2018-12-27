package com.convidad.banking.service;

import java.util.List;

import com.convidad.banking.controller.request.UpdateBalanceRequest;
import com.convidad.banking.model.Account;

public interface IAccountService {

	String createAccount(String userId);

	void deposit(UpdateBalanceRequest request);

	void withdraw(UpdateBalanceRequest request);
	
	Account getAccount(String accountNumber);

	List<Account> getUserAccounts(String userId);
}
