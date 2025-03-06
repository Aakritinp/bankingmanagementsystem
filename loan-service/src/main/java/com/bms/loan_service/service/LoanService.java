package com.bms.loan_service.service;



import com.bms.card_service.model.Loan;
import com.bms.card_service.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

   public Loan getAllById(String id) {
       if (!loanRepository.existsById(id)) {
           return null;
       }
        return loanRepository.findById(id).get();
    }





}

