package com.bms.security_service.model;

import lombok.Data;

@Data
public class User {
    private String id;
    private String name;
    private String address;
    private String email;
    private String password;
    private String role;
    private String username;

}
