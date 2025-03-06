package com.bms.user_service.service;

import com.bms.user_service.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    User loginUser(String userName, String password);

    User getUserById(String id);

    List<User> getAllUsers();

    User updateUser(User user, String id);

    void deleteUser(String id);
}
