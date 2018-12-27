package com.convidad.banking.service.account;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.convidad.banking.controller.request.UpdateBalanceRequest;
import com.convidad.banking.model.Account;
import com.convidad.banking.repository.AccountRepository;
import com.convidad.banking.service.impl.AccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AccountServiceTest {

	@InjectMocks
	private AccountService accountServiceMock;

	@Mock
	private AccountRepository accountRepositoryMock;

	@Autowired
	private AccountService accountService;

	@Autowired
	private AccountRepository accountRepository;

	private static final String userTest = "User1";
	
	@Test
	public void createAccount_reject() {
		Assert.assertNull(accountServiceMock.createAccount(""));
	}

	@Test
	public void createAccount_accept() {
		Mockito.when(accountRepositoryMock.save(Mockito.any())).thenReturn(Account.create().setUserId(userTest).build());
		Assert.assertNotNull(accountServiceMock.createAccount(userTest));
	}

	@Test
	public void deposit_accept() {
		Account account = accountRepository.save(Account.create().setUserId(userTest).build());
		UpdateBalanceRequest request = new UpdateBalanceRequest();
		request.setUserId(userTest);
		request.setAmount(20);
		request.setAccountId(account.getAccountNumber());
		accountService.deposit(request);
		
		account = accountRepository.findByAccountNumber(account.getAccountNumber());
		Assert.assertNotNull(account);
		Assert.assertEquals(new Double(20), new Double(account.getBalance()));
		Assert.assertEquals(userTest, account.getUserId());
		Assert.assertNotNull(account.getAccountNumber());
	}
	
	@Test
	public void withdraw_accept() {
		Account account = Account.create().setUserId(userTest).build();
		account.deposit(500);
		accountRepository.save(account);
		UpdateBalanceRequest request = new UpdateBalanceRequest();
		request.setUserId(userTest);
		request.setAmount(200);
		request.setAccountId(account.getAccountNumber());
		
		accountService.withdraw(request);
		account = accountRepository.findByAccountNumber(account.getAccountNumber());
		Assert.assertNotNull(account);
		Assert.assertEquals(new Double(300), new Double(account.getBalance()));
		Assert.assertEquals(userTest, account.getUserId());
		Assert.assertNotNull(account.getAccountNumber());
	}
	
	@Test
	public void getAccount_accept() {
		Account account = Account.create().setUserId(userTest).build();
		account.deposit(100);
		accountRepository.save(account);
		final String accountNumber = account.getAccountNumber();
		
		account = accountService.getAccount(accountNumber);
		Assert.assertNotNull(account);
		Assert.assertEquals(userTest, account.getUserId());
		Assert.assertEquals(accountNumber, account.getAccountNumber());
		Assert.assertEquals(new Double(100), new Double(account.getBalance()));
	}
	
	@Test
	public void getUserAccounts_accept() {
		Account account = Account.create().setUserId(userTest).build();
		account.deposit(100);
		accountRepository.save(account);
		account = Account.create().setUserId(userTest).build();
		account.deposit(500);
		accountRepository.save(account);
		account = Account.create().setUserId(userTest).build();
		account.deposit(10000);
		accountRepository.save(account);
		
		List<Account> accounts = accountService.getUserAccounts(userTest);
		Assert.assertNotNull(accounts);
		Assert.assertFalse(accounts.isEmpty());
		Assert.assertEquals(3, accounts.size());
		Assert.assertTrue(accounts.stream().allMatch(x -> x.getUserId().equals(userTest)));
		Assert.assertTrue(accounts.stream().anyMatch(x -> x.getBalance() == 10000));
		Assert.assertTrue(accounts.stream().anyMatch(x -> x.getBalance() == 100));
		Assert.assertTrue(accounts.stream().anyMatch(x -> x.getBalance() == 500));
	}
}
