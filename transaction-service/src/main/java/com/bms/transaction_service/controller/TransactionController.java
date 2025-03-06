package com.bms.transaction_service.controller;

import com.bms.transaction_service.model.Account;
import com.bms.transaction_service.model.Transaction;
import com.bms.transaction_service.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {


    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<Transaction> deposit(@RequestBody Transaction transaction) {
        return ResponseEntity.ok(transactionService.deposit(transaction));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Transaction> withdraw(@RequestBody Transaction transaction) {
        try {
            return ResponseEntity.ok(transactionService.withdraw(transaction));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/balance/{accountId}")
    public ResponseEntity<Double> getBalance(@PathVariable String accountId) {
        return ResponseEntity.ok(transactionService.getBalance(accountId));
    }

    @GetMapping("/statement/{accountId}")
    public ResponseEntity<List<Transaction>> getStatement(@PathVariable String accountId) {
        return ResponseEntity.ok(transactionService.generateStatement(accountId));
    }

}
