package com.gcu.bookstore.service;

import org.springframework.stereotype.Service;

import com.gcu.bookstore.model.User;

@Service
public class UserService {

    public boolean authenticate(String username, String password) {
        // Basic mock authentication
        return "admin".equals(username) && "password".equals(password);
    }

    public boolean register(User user) {
        // Basic mock registration validation
        return user.getPassword().equals(user.getConfirmPassword());
    }
}
