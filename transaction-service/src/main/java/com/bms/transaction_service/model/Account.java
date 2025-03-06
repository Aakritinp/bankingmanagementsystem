package com.bms.transaction_service.model;

import lombok.Data;

@Data
public class Account {
    private String id;
    private String userId;
    private String type;
    private String number;
    private Double balance;
    private String status;

}
