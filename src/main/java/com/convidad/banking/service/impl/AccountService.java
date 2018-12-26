package com.convidad.banking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.convidad.banking.model.Account;
import com.convidad.banking.repository.AccountRepository;
import com.convidad.banking.service.IAccountService;

public class AccountService implements IAccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public String createAccount(String userId) {
		String accountId = null;
		if (!StringUtils.isEmpty(userId)) {
			accountId = accountRepository.save(Account.create().setUserId(userId).build()).getAccountNumber();
		}
		return accountId;
	}

}
