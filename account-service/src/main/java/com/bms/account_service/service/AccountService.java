package com.bms.account_service.service;


import com.bms.account_service.model.Account;
import com.bms.account_service.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Optional<Account> getAccountById(String id) {
        return accountRepository.findById(id);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account updateAccount(Account account, String id) {
        if (!accountRepository.existsById(id)) {
            return null;
        }
        account.setId(id);
        return accountRepository.save(account);
    }

    public Account updateBalance(Account account, String transactionType) {
        if (!accountRepository.existsById(account.getId())) {
            return null;
        }
        Account existingAccount = accountRepository.findById(account.getId()).get();
        if (transactionType.equals("Deposit")) {
            existingAccount.setBalance(existingAccount.getBalance() + account.getBalance());
        } else if (transactionType.equals("Withdraw")) {
            existingAccount.setBalance(existingAccount.getBalance() - account.getBalance());
        } else {
            return null;
        }
        return accountRepository.save(existingAccount);
    }

    public void deleteAccount(String id) {
        if (!accountRepository.existsById(id)) {
            return;
        }
        accountRepository.deleteById(id);
    }

    public Account getAccountByUserId(String userId) {
        return accountRepository.findByUserId(userId);
    }

    public Account getAccountByAccountNumber(String accountNumber) {
        return accountRepository.findByNumber(accountNumber);
    }
}

