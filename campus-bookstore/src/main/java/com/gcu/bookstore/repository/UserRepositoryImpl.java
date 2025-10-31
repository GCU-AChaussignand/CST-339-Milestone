package com.gcu.bookstore.repository;

import com.gcu.bookstore.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

/**
 * JDBC implementation of UserRepository interface
 * Uses Spring JdbcTemplate for database operations
 * Implements DAO pattern for data access
 * 
 * @author [Your Names]
 * @version 1.0
 * @since 2025-10-30
 */
@Repository
public class UserRepositoryImpl implements UserRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    /**
     * RowMapper to convert database rows to UserModel objects
     */
    private final RowMapper<UserModel> userRowMapper = (rs, rowNum) -> {
        UserModel user = new UserModel();
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setPhoneNumber(rs.getString("phone_number"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        return user;
    };
    
    /**
     * Saves a new user to the database
     * @param user The user to save
     * @return The saved user
     */
    @Override
    public UserModel save(UserModel user) {
        String sql = "INSERT INTO users (first_name, last_name, email, phone_number, username, password) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPhoneNumber());
            ps.setString(5, user.getUsername());
            ps.setString(6, user.getPassword());
            return ps;
        }, keyHolder);
        
        return user;
    }
    
    /**
     * Finds a user by username
     * @param username The username to search for
     * @return The user if found, null otherwise
     */
    @Override
    public UserModel findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try {
            return jdbcTemplate.queryForObject(sql, userRowMapper, username);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    /**
     * Finds a user by ID
     * @param id The user ID
     * @return The user if found, null otherwise
     */
    @Override
    public UserModel findById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, userRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    /**
     * Retrieves all users from the database
     * @return List of all users
     */
    @Override
    public List<UserModel> findAll() {
        String sql = "SELECT * FROM users ORDER BY username";
        return jdbcTemplate.query(sql, userRowMapper);
    }
    
    /**
     * Checks if a username exists in the database
     * @param username The username to check
     * @return true if username exists, false otherwise
     */
    @Override
    public boolean existsByUsername(String username) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, username);
        return count != null && count > 0;
    }
    
    /**
     * Updates an existing user
     * @param user The user with updated information
     * @return true if update successful
     */
    @Override
    public boolean update(UserModel user) {
        String sql = "UPDATE users SET first_name = ?, last_name = ?, email = ?, " +
                     "phone_number = ?, password = ? WHERE username = ?";
        int rowsAffected = jdbcTemplate.update(sql,
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getPhoneNumber(),
            user.getPassword(),
            user.getUsername()
        );
        return rowsAffected > 0;
    }
    
    /**
     * Deletes a user by ID
     * @param id The user ID to delete
     * @return true if deletion successful
     */
    @Override
    public boolean deleteById(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }
    
    /**
     * Counts total number of users
     * @return Total user count
     */
    @Override
    public int count() {
        String sql = "SELECT COUNT(*) FROM users";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count != null ? count : 0;
    }
}