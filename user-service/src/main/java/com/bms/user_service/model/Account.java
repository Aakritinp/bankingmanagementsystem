package com.bms.user_service.model;

import lombok.Data;

@Data
public class Account {
    private String userId;
    private String type;
    private String number;
    private Double balance;
    private String status;
    private String name;

}
