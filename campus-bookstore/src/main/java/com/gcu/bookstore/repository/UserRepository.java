package com.gcu.bookstore.repository;

import com.gcu.bookstore.model.UserModel;
import java.util.List;

/**
 * Repository interface for User data access operations
 * Defines contract for CRUD operations on User entities
 * 
 * @author [Your Names]
 * @version 1.0
 * @since 2025-10-30
 */
public interface UserRepository {
    
    /**
     * Saves a new user to the database
     * @param user The user to save
     * @return The saved user with generated ID
     */
    UserModel save(UserModel user);
    
    /**
     * Finds a user by username
     * @param username The username to search for
     * @return The user if found, null otherwise
     */
    UserModel findByUsername(String username);
    
    /**
     * Finds a user by ID
     * @param id The user ID
     * @return The user if found, null otherwise
     */
    UserModel findById(int id);
    
    /**
     * Retrieves all users from the database
     * @return List of all users
     */
    List<UserModel> findAll();
    
    /**
     * Checks if a username exists in the database
     * @param username The username to check
     * @return true if username exists, false otherwise
     */
    boolean existsByUsername(String username);
    
    /**
     * Updates an existing user
     * @param user The user with updated information
     * @return true if update successful, false otherwise
     */
    boolean update(UserModel user);
    
    /**
     * Deletes a user by ID
     * @param id The user ID to delete
     * @return true if deletion successful, false otherwise
     */
    boolean deleteById(int id);
    
    /**
     * Counts total number of users
     * @return Total user count
     */
    int count();
}