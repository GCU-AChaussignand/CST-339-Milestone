package com.gcu.bookstore.model;

import jakarta.validation.constraints.NotEmpty;

/**
 * Login model for user authentication
 * Represents login credentials
 */
public class LoginModel {
    
    @NotEmpty(message = "Username is required")
    private String username;
    
    @NotEmpty(message = "Password is required")
    private String password;
    
    // Constructors
    public LoginModel() {
    }
    
    public LoginModel(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    // Getters and Setters
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}