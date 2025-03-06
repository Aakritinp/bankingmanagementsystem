package com.bms.transaction_service.service;

import com.bms.transaction_service.model.Account;
import com.bms.transaction_service.model.Transaction;
import com.bms.transaction_service.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction deposit(Transaction transaction) {
        transaction.setType("Deposit");
        transaction.setDate(LocalDate.now().toString());
        transaction = transactionRepository.save(transaction);
        updateAccountBalance(transaction.getAccountId(), transaction.getAmount(), transaction.getType());
        return transaction;
    }

    public Transaction withdraw(Transaction transaction) {
        transaction.setType("Withdraw");
        transaction.setDate(LocalDate.now().toString());
        transaction = transactionRepository.save(transaction);
        updateAccountBalance(transaction.getAccountId(), transaction.getAmount(), transaction.getType());
        return transaction;
    }

    private void updateAccountBalance(String accountId, double amount, String transactionType) {
        // Define the API URL (account-service endpoint)
        String accountServiceUrl = "http://account-service/accounts/balance?transactionType=" + transactionType;

        // Prepare the request payload with accountId and updated balance
        Account account = new Account();
        account.setId(accountId);
        account.setBalance(amount);

        // Call the account-service API to update the balance
        restTemplate.put(accountServiceUrl, account); // PUT request to update balance
    }

    public List<Transaction> getTransactionHistory(String accountId) {
        return transactionRepository.findAllByAccountId(accountId);
    }

    public Double getBalance(String accountId) {
       return null;
    }

    public List<Transaction> generateStatement(String accountId) {
        return getTransactionHistory(accountId);
    }
}