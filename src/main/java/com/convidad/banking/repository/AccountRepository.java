package com.convidad.banking.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.convidad.banking.model.Account;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {

}
