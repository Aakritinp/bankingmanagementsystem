package com.bms.user_service.controller;

import com.bms.user_service.model.User;
import com.bms.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        if (savedUser != null) {
            return ResponseEntity.ok(savedUser);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User savedUser = userService.loginUser(user.getUsername(), user.getPassword());
        if (savedUser != null) {
            return ResponseEntity.ok(savedUser);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        User getUserById = userService.getUserById(id);
        if (getUserById != null) {
            return ResponseEntity.ok(getUserById);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllUsers() {
        List<User> getAllUsers = userService.getAllUsers();
        if (getAllUsers != null) {
            return ResponseEntity.ok(getAllUsers);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody User user) {
        User updateUser = userService.updateUser(user, id);
        if (updateUser != null) {
            return ResponseEntity.ok(updateUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

}
