package com.bms.loan_service.model;

import lombok.Data;

@Data
public class Loan {

    private String id;
    private String accountId;
    private Double amount;
    private Integer duration;
    private String status;
    private String date;

}
