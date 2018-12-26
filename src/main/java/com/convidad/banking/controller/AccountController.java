package com.convidad.banking.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.convidad.banking.controller.request.CreateAccountRequest;
import com.convidad.banking.controller.response.CreateAccountResponse;
import com.convidad.banking.service.IAccountService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	@Autowired
	private IAccountService accountService;
	
	@ApiOperation(value = "Create an account binded to user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE,
			notes = "Provide a valid userId")
	@PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	public CreateAccountResponse createAccount(@RequestBody CreateAccountRequest request) {
		return new CreateAccountResponse(accountService.createAccount(request.getUserId()));
	}
}
