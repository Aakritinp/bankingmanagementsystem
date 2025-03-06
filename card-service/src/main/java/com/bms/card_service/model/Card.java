package com.bms.card_service.model;

import lombok.Data;

@Data
public class Card {

    private String id;
    private String Number;
    private String Type;
    private String Status;
    private String HolderName;
    private String ExpiryDate;
    private String cvv;

}
