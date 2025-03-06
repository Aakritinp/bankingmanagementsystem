package com.bms.giftcard_service.model;

import lombok.Data;

@Data
public class GiftCard {

    private String id;
    private String Number;
    private String Status;
    private String HolderName;
    private String ExpiryDate;
    private String cvv;

}
