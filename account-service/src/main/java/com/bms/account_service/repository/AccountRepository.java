package com.bms.account_service.repository;


import com.bms.account_service.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
    Account findByUserId(String userId);

    Account findByNumber(String accountNumber);
}
