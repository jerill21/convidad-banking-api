package com.convidad.banking.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.convidad.banking.model.Account;

public interface AccountRepository extends MongoRepository<Account, String> {

}
