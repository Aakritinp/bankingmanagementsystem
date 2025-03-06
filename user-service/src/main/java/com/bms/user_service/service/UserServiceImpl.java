package com.bms.user_service.service;

import com.bms.user_service.model.Account;
import com.bms.user_service.model.User;
import com.bms.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private String securityServiceUrl = "http://security-service/auth/token";
    private String accountServiceUrl = "http://account-service/accounts";

    @Override
    public User saveUser(User user) {
        if (user.getUsername() != null && userRepository.findByUsername(user.getUsername()) == null) {
            user = userRepository.save(user);
            if (user.getRole().equals("Customer")) {
                Account account = new Account();
                account.setType("Checking");
                account.setUserId(user.getId());
                account.setStatus("Active");
                account.setNumber(UUID.randomUUID().toString());
                account.setBalance(0.0);
                account.setName(user.getName());
                restTemplate.postForObject(accountServiceUrl, account, Account.class);
            }

            return user;
        }
        return null;
    }

    @Override
    public User loginUser(String userName, String password) {
        User user = userRepository.findByUsernameAndPassword(userName, password);
        if (user != null) {
            String jwtToken = restTemplate.getForObject(securityServiceUrl + "?id=" + user.getId() + "&role=" + user.getRole(), String.class);
            user.setToken(jwtToken);
            return user;
        }
        return null;
    }

    @Override
    public User getUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user, String id) {
        if (!userRepository.existsById(id)) {
            return null;
        }
        user.setId(id);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String id) {
        if (!userRepository.existsById(id)) {
            return;
        }
        userRepository.deleteById(id);
    }

}
