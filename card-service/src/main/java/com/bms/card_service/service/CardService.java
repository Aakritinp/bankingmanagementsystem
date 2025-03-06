package com.bms.card_service.service;




import com.bms.card_service.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    }






