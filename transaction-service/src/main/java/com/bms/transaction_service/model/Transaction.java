package com.bms.transaction_service.model;

import lombok.Data;

@Data
public class Transaction {
    String Id;
    String accountId;
    String type;
    String from;
    String to;
    Double amount;
    String date;
    String proceedBy;
}
