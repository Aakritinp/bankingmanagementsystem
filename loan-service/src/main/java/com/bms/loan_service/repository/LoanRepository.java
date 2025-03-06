package com.bms.loan_service.repository;




import com.bms.loan_service.model.Loan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LoanRepository extends MongoRepository<Loan, String> {
}
