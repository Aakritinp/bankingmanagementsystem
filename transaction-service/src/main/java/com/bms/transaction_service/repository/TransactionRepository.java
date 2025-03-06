package com.bms.transaction_service.repository;

import com.bms.transaction_service.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findAllByAccountId(String accountId);
}
