package com.bms.giftcard_service.service;




import com.bms.giftcard_service.repository.GiftCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class GiftCardService {

    @Autowired
    private GiftCardRepository giftCardRepository;

    }







