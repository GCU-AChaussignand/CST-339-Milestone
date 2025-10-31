package com.gcu.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.bookstore.model.UserModel;
import com.gcu.bookstore.repository.UserRepository;

/**
 * Service class for user-related business logic
 * Acts as intermediary between controllers and repository
 * Contains business rules and validation
 * 
 * @author [Your Names]
 * @version 1.0
 * @since 2025-10-30
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Registers a new user in the system
     * Validates that username doesn't already exist
     * 
     * @param newUser The user to register
     * @return true if registration successful, false if username exists
     */
    public boolean registerUser(UserModel newUser) {
        System.out.println("=== REGISTER USER ===");
        System.out.println("Attempting to register: " + newUser.getUsername());
        
        // Check if username already exists
        if (usernameExists(newUser.getUsername())) {
            System.out.println("ERROR: Username already exists!");
            return false;
        }

        // In production, you should hash the password here
        // newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        // Save user to database
        UserModel savedUser = userRepository.save(newUser);
        System.out.println("User registered successfully: " + savedUser.getUsername());
        System.out.println("Total users now: " + getTotalUsers());
        return true;
    }

    /**
     * Checks if a username already exists in the database
     * 
     * @param username The username to check
     * @return true if username exists, false otherwise
     */
    public boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    /**
     * Authenticates a user with username and password
     * 
     * @param username The username
     * @param password The password
     * @return UserModel if authentication successful, null otherwise
     */
    public UserModel authenticateUser(String username, String password) {
        System.out.println("=== AUTHENTICATE USER ===");
        System.out.println("Looking for username: " + username);
        System.out.println("Total users in system: " + getTotalUsers());

        // Find user by username
        UserModel user = userRepository.findByUsername(username);

        if (user != null) {
            System.out.println("User found: " + user.getUsername());
            
            // In production, use password encoder to compare hashed passwords
            // if (passwordEncoder.matches(password, user.getPassword()))
            
            if (user.getPassword().equals(password)) {
                System.out.println("Password matches! Authentication successful");
                return user;
            } else {
                System.out.println("Password does NOT match. Expected: '" + user.getPassword() + "' Got: '" + password + "'");
            }
        } else {
            System.out.println("No user found with username: " + username);
        }

        return null;
    }

    /**
     * Retrieves a user by username
     * 
     * @param username The username to search for
     * @return UserModel if found, null otherwise
     */
    public UserModel getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Retrieves all users from the database
     * 
     * @return List of all users
     */
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Gets the total count of users in the system
     * 
     * @return Total number of users
     */
    public int getTotalUsers() {
        return userRepository.count();
    }

    /**
     * Checks if a user has admin privileges
     * Currently hardcoded - should be database-driven in production
     * 
     * @param username The username to check
     * @return true if user is admin, false otherwise
     */
    public boolean isAdmin(String username) {
        // In production, this should check a role column in the database
        return "admin".equalsIgnoreCase(username);
    }

    /**
     * Updates an existing user's information
     * 
     * @param user The user with updated information
     * @return true if update successful, false otherwise
     */
    public boolean updateUser(UserModel user) {
        return userRepository.update(user);
    }

    /**
     * Deletes a user from the system
     * 
     * @param id The ID of the user to delete
     * @return true if deletion successful, false otherwise
     */
    public boolean deleteUser(int id) {
        return userRepository.deleteById(id);
    }
}