package com.convidad.banking;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.convidad.banking.service.IAccountService;
import com.convidad.banking.service.impl.AccountService;

@Configuration
public class AppConfig {

	@Bean
	public IAccountService accountService() {
		return new AccountService();
	}
}
