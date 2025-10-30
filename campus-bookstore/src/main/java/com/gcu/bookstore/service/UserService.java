package com.gcu.bookstore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gcu.bookstore.model.UserModel;

@Service
public class UserService {
    
    private final List<UserModel> users;

    public UserService() {
        users = new ArrayList<>();
        initializeDefaultUsers();
    }
    

    private void initializeDefaultUsers() {
        UserModel admin = new UserModel();
        admin.setFirstName("Admin");
        admin.setLastName("User");
        admin.setEmail("admin@bookstore.com");
        admin.setPhoneNumber("555-123-4567");
        admin.setUsername("admin");
        admin.setPassword("password");
        users.add(admin);
        
        UserModel user = new UserModel();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("user@bookstore.com");
        user.setPhoneNumber("555-987-6543");
        user.setUsername("user");
        user.setPassword("password");
        users.add(user);
    }
    
 
    public boolean registerUser(UserModel newUser) {
        if (usernameExists(newUser.getUsername())) {
            return false;
        }
        
        users.add(newUser);
        return true;
    }
    
    
    public boolean usernameExists(String username) {
        return users.stream()
                .anyMatch(user -> user.getUsername().equalsIgnoreCase(username));
    }
    
   
    public UserModel authenticateUser(String username, String password) {
        System.out.println("=== AUTHENTICATE USER ===");
        System.out.println("Looking for username: " + username);
        System.out.println("Checking against " + users.size() + " users");
        
        for (UserModel user : users) {
            System.out.println("  - Checking user: " + user.getUsername() + " / password: " + user.getPassword());
            if (user.getUsername().equalsIgnoreCase(username)) {
                System.out.println("    Username match found!");
                if (user.getPassword().equals(password)) {
                    System.out.println("    Password matches!");
                    return user;
                } else {
                    System.out.println("    Password does NOT match. Expected: '" + user.getPassword() + "' Got: '" + password + "'");
                }
            }
        }
        
        System.out.println("No matching user found!");
        return null;
    }
    
   
    public UserModel getUserByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElse(null);
    }
    

    public List<UserModel> getAllUsers() {
        return new ArrayList<>(users);
    }
    
  
    public int getTotalUsers() {
        return users.size();
    }

    public boolean isAdmin(String username) {
        return "admin".equalsIgnoreCase(username);
    }
}