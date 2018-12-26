package com.convidad.banking.service.account;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.convidad.banking.model.Account;
import com.convidad.banking.repository.AccountRepository;
import com.convidad.banking.service.impl.AccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AccountServiceTest {

	@InjectMocks
	private AccountService accountService;

	@Mock
	private AccountRepository accountRepository;

	@Test
	public void createAccount_reject() {
		Assert.assertNull(accountService.createAccount(""));
	}

	@Test
	public void createAccount_accept() {
		Mockito.when(accountRepository.save(Mockito.any())).thenReturn(Account.create().setUserId("User1").build());
		Assert.assertNotNull(accountService.createAccount("User1"));
	}
}
