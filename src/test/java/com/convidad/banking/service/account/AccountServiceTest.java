package com.convidad.banking.service.account;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.convidad.banking.controller.AccountController;
import com.convidad.banking.controller.request.CreateAccountRequest;
import com.convidad.banking.controller.response.CreateAccountResponse;
import com.convidad.banking.service.IAccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AccountServiceTest {

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
	
	
}
