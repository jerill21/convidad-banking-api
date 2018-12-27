package com.convidad.banking.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.convidad.banking.controller.request.CreateAccountRequest;
import com.convidad.banking.controller.response.AccountResponse;
import com.convidad.banking.controller.response.CreateAccountResponse;
import com.convidad.banking.controller.response.UserAccountsResponse;
import com.convidad.banking.model.Account;
import com.convidad.banking.service.IAccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AccountControllerTest {

	@InjectMocks
	private AccountController accountController;

	@Mock
	private IAccountService accountService;
	
	@Test
	public void createAccount_emptyUser_reject() {
		
		Mockito.when(accountService.createAccount("")).thenReturn(null);
		
		CreateAccountRequest request = new CreateAccountRequest();
		request.setUserId("");
		CreateAccountResponse response = accountController.createAccount(request);
		
		Assert.assertNotNull(response);
		Assert.assertNull(response.getAccountId());
	}
	
	@Test
	public void createAccount_accept() {
		String accountId = UUID.randomUUID().toString();
		Mockito.when(accountService.createAccount("user1")).thenReturn(accountId);
		
		CreateAccountRequest request = new CreateAccountRequest();
		request.setUserId("user1");
		CreateAccountResponse response = accountController.createAccount(request);
		
		Assert.assertNotNull(response);
		Assert.assertEquals(accountId, response.getAccountId());
	}
	
	@Test
	public void getAccount_accept() {
		Account account = Account.create().setUserId("user1").build();
		Mockito.when(accountService.getAccount(account.getAccountNumber())).thenReturn(account);
		
		AccountResponse response = accountController.getAccount(account.getAccountNumber());
		Assert.assertNotNull(response);
		Assert.assertEquals("user1", response.getUserId());
		Assert.assertEquals(account.getAccountNumber(), response.getAccountNumber());
		Assert.assertEquals(new Double(0), new Double(response.getBalance()));
	}
	
	@Test
	public void getUserAccounts_accept() {
		List<Account> accounts = new ArrayList<>();
		final Account account1 = Account.create().setUserId("user1").build();
		account1.deposit(20);
		accounts.add(account1);
		final Account account2 = Account.create().setUserId("user1").build();
		account2.deposit(300);
		accounts.add(account2);
		final Account account3 = Account.create().setUserId("user1").build();
		account3.deposit(240);
		accounts.add(account3);
		Mockito.when(accountService.getUserAccounts("user1")).thenReturn(accounts);
		
		UserAccountsResponse response = accountController.getUserAccounts("user1");
		Assert.assertNotNull(response);
		Assert.assertEquals("user1", response.getUserId());
		Assert.assertEquals("user1", response.getUserId());
		Assert.assertEquals(3, response.getAccountResponse().size());
		Assert.assertTrue(response.getAccountResponse().stream().anyMatch(x -> x.getBalance() == 20));
		Assert.assertTrue(response.getAccountResponse().stream().anyMatch(x -> x.getBalance() == 300));
		Assert.assertTrue(response.getAccountResponse().stream().anyMatch(x -> x.getBalance() == 240));
		Assert.assertTrue(response.getAccountResponse().stream().allMatch(x -> x.getAccountNumber() != null));
	}
}
