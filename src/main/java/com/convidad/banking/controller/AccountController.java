package com.convidad.banking.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.convidad.banking.controller.request.CreateAccountRequest;
import com.convidad.banking.controller.request.UpdateBalanceRequest;
import com.convidad.banking.controller.response.AccountResponse;
import com.convidad.banking.controller.response.CreateAccountResponse;
import com.convidad.banking.controller.response.UserAccountsResponse;
import com.convidad.banking.model.Account;
import com.convidad.banking.service.IAccountService;

import io.swagger.annotations.ApiOperation;

@RestController
public class AccountController {

	@Autowired
	private IAccountService accountService;

	@ApiOperation(value = "Create an account binded to user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE, notes = "Provide a valid userId")
	@PostMapping(value = "/accounts", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	public CreateAccountResponse createAccount(@RequestBody CreateAccountRequest request) {
		return new CreateAccountResponse(accountService.createAccount(request.getUserId()));
	}

	@ApiOperation(value = "Deposit money to user account", consumes = MediaType.APPLICATION_JSON_VALUE, notes = "Provide a valid account number")
	@PatchMapping(value = "/accounts/deposit", consumes = APPLICATION_JSON_VALUE)
	public void deposit(@RequestBody UpdateBalanceRequest request) {
		accountService.deposit(request);
	}

	@ApiOperation(value = "Withdraw money from user account", consumes = MediaType.APPLICATION_JSON_VALUE, notes = "Provide a valid account number")
	@PatchMapping(value = "/accounts/withdraw", consumes = APPLICATION_JSON_VALUE)
	public void withdraw(@RequestBody UpdateBalanceRequest request) {
		accountService.withdraw(request);
	}

	@ApiOperation(value = "Get account information", consumes = MediaType.APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE, notes = "Provide a valid accountNumber")
	@GetMapping(value = "/accounts/{accountNumber}", produces = APPLICATION_JSON_VALUE)
	public AccountResponse getAccount(@PathVariable String accountNumber) {
		Account account = accountService.getAccount(accountNumber);
		return new AccountResponse(account.getAccountNumber(), account.getUserId(), account.getBalance());
	}

	@ApiOperation(value = "Get user accounts", produces = APPLICATION_JSON_VALUE, notes = "Provide a valid userId")
	@GetMapping(value = "/accounts/user/{userId}", produces = APPLICATION_JSON_VALUE)
	public UserAccountsResponse getUserAccounts(@PathVariable String userId) {
		return new UserAccountsResponse(userId, accountService.getUserAccounts(userId).stream()
				.map(x -> new AccountResponse(x.getAccountNumber(), x.getUserId(), x.getBalance())).collect(Collectors.toList()));
	}
}
