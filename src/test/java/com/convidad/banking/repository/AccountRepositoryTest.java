package com.convidad.banking.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.convidad.banking.model.Account;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AccountRepositoryTest {

	@Autowired
	private AccountRepository accountRepository;

	@Before
	public void beforeTest() {
		accountRepository.deleteAll();
	}
	
	private static final String userTest = "User1";
	
	@Test
	public void createAccount_accept() {
		Account account = accountRepository.save(Account.create().setUserId(userTest).build());
		Assert.assertNotNull(account);
		Assert.assertEquals(userTest, account.getUserId());
		Assert.assertEquals(new Double(0), new Double(account.getBalance()));
		Assert.assertNotNull(account.getAccountNumber());
		Assert.assertNotNull(account.getAudit());
		Assert.assertNotNull(account.getAudit().getCreationDate());
	}
	
	@Test
	public void findByAccountNumber_reject() {
		Assert.assertNull(accountRepository.findByAccountNumber("made_up_account"));
	}
	
	@Test
	public void findByAccountNumber_accept() {
		Account account = accountRepository.save(Account.create().setUserId(userTest).build());
		Assert.assertNotNull(accountRepository.findByAccountNumber(account.getAccountNumber()));
	}
}
