package com.convidad.banking.repository;

import org.junit.Assert;
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

	@Test
	public void createAttempt_accept() {
		Account account = accountRepository.save(Account.create().setUserId("User1").build());
		Assert.assertNotNull(account);
		Assert.assertEquals("User1", account.getUserId());
		Assert.assertEquals(new Double(0), new Double(account.getBalance()));
		Assert.assertNotNull(account.getAccountNumber());
		Assert.assertNotNull(account.getAudit());
		Assert.assertNotNull(account.getAudit().getCreationDate());
	}
}
