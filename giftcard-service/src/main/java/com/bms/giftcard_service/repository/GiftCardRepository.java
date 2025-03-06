package com.bms.giftcard_service.repository;




import com.bms.giftcard_service.model.GiftCard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GiftCardRepository extends MongoRepository<GiftCard, String> {
}
