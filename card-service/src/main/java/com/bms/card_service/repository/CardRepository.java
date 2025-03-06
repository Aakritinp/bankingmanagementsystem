package com.bms.card_service.repository;



import com.bms.card_service.model.Card;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CardRepository extends MongoRepository<Card, String> {
}
