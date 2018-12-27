package com.convidad.banking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.convidad.banking.controller.request.UpdateBalanceRequest;
import com.convidad.banking.model.Account;
import com.convidad.banking.repository.AccountRepository;
import com.convidad.banking.service.IAccountService;

public class AccountService implements IAccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public String createAccount(final String userId) {
		String accountId = null;
		if (!StringUtils.isEmpty(userId)) {
			accountId = accountRepository.save(Account.create().setUserId(userId).build()).getAccountNumber();
		}
		return accountId;
	}

	@Override
	public void deposit(final UpdateBalanceRequest request) {
		Account account = accountRepository.findByAccountNumber(request.getAccountId());
		if (account != null) {
			account.deposit(request.getAmount());
			accountRepository.save(account);
		}
	}

	@Override
	public void withdraw(final UpdateBalanceRequest request) {
		Account account = accountRepository.findByAccountNumber(request.getAccountId());
		if (account != null) {
			account.withdraw(request.getAmount());
			accountRepository.save(account);
		}		
	}

	@Override
	public Account getAccount(final String accountNumber) {
		return accountRepository.findByAccountNumber(accountNumber);
	}

	@Override
	public List<Account> getUserAccounts(final String userId) {
		return accountRepository.findByUserId(userId);
	}
}
