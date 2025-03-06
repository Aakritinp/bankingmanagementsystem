package com.bms.user_service.repository;

import com.bms.user_service.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByUsernameAndPassword(String username, String password);
    User findByUsername(String username);
}
