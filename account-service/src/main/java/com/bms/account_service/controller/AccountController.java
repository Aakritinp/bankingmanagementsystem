package com.bms.account_service.controller;


import com.bms.account_service.model.Account;
import com.bms.account_service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping()
    public ResponseEntity<?> createAccount(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.createAccount(account));
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<?> getAccountById(@PathVariable String id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @GetMapping()
    public ResponseEntity<?> getAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/search/{accountNumber}")
    public ResponseEntity<?> getAccountByAccountNumber(@PathVariable String accountNumber) {
        return ResponseEntity.ok(accountService.getAccountByAccountNumber(accountNumber));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getAccountByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(accountService.getAccountByUserId(userId));
    }

//    @GetMapping("/balance/{id}")
//    public ResponseEntity<?> getBalance(@PathVariable String id) {
//        return accountService.getBalance(id)
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }

    @PatchMapping("/{accountId}")
    public ResponseEntity<?> updateAccount(@PathVariable String id, @RequestBody Account account) {
        return ResponseEntity.ok(accountService.updateAccount(account, id));
    }

    @PutMapping("/balance")
    public ResponseEntity<?> updateBalance(@RequestParam String transactionType, @RequestBody Account account) {
        return ResponseEntity.ok(accountService.updateBalance(account, transactionType));
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable String id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok().build();
    }
}

