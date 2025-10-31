package com.gcu.bookstore.repository;

import com.gcu.bookstore.model.BookModel;
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
 * JDBC implementation of BookRepository interface
 * Uses Spring JdbcTemplate for database operations
 * Implements DAO pattern for data access
 * 
 * @author [Your Names]
 * @version 1.0
 * @since 2025-10-30
 */
@Repository
public class BookRepositoryImpl implements BookRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    /**
     * RowMapper to convert database rows to BookModel objects
     */
    private final RowMapper<BookModel> bookRowMapper = (rs, rowNum) -> {
        BookModel book = new BookModel();
        book.setId(rs.getLong("id"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setIsbn(rs.getString("isbn"));
        book.setCourse(rs.getString("course"));
        book.setSubject(rs.getString("subject"));
        book.setPrice(rs.getDouble("price"));
        book.setStock(rs.getInt("stock"));
        book.setImageUrl(rs.getString("image_url"));
        book.setDescription(rs.getString("description"));
        return book;
    };
    
    /**
     * Saves a new book to the database
     * @param book The book to save
     * @return The saved book with generated ID
     */
    @Override
    public BookModel save(BookModel book) {
        String sql = "INSERT INTO books (title, author, isbn, course, subject, price, stock, image_url, description) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getIsbn());
            ps.setString(4, book.getCourse());
            ps.setString(5, book.getSubject());
            ps.setDouble(6, book.getPrice());
            ps.setInt(7, book.getStock());
            ps.setString(8, book.getImageUrl());
            ps.setString(9, book.getDescription());
            return ps;
        }, keyHolder);
        
        if (keyHolder.getKey() != null) {
            book.setId(keyHolder.getKey().longValue());
        }
        
        return book;
    }
    
    /**
     * Finds a book by ID
     * @param id The book ID
     * @return The book if found, null otherwise
     */
    @Override
    public BookModel findById(Long id) {
        String sql = "SELECT * FROM books WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, bookRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    /**
     * Finds a book by ISBN
     * @param isbn The ISBN to search for
     * @return The book if found, null otherwise
     */
    @Override
    public BookModel findByIsbn(String isbn) {
        String sql = "SELECT * FROM books WHERE isbn = ?";
        try {
            return jdbcTemplate.queryForObject(sql, bookRowMapper, isbn);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    /**
     * Retrieves all books from the database
     * @return List of all books
     */
    @Override
    public List<BookModel> findAll() {
        String sql = "SELECT * FROM books ORDER BY title";
        return jdbcTemplate.query(sql, bookRowMapper);
    }
    
    /**
     * Searches books by query string
     * @param query Search term to match against title, author, ISBN, course, or subject
     * @return List of matching books
     */
    @Override
    public List<BookModel> search(String query) {
        String sql = "SELECT * FROM books WHERE " +
                     "LOWER(title) LIKE LOWER(?) OR " +
                     "LOWER(author) LIKE LOWER(?) OR " +
                     "LOWER(isbn) LIKE LOWER(?) OR " +
                     "LOWER(course) LIKE LOWER(?) OR " +
                     "LOWER(subject) LIKE LOWER(?) " +
                     "ORDER BY title";
        
        String searchPattern = "%" + query + "%";
        return jdbcTemplate.query(sql, bookRowMapper, 
            searchPattern, searchPattern, searchPattern, searchPattern, searchPattern);
    }
    
    /**
     * Updates an existing book
     * @param book The book with updated information
     * @return true if update successful
     */
    @Override
    public boolean update(BookModel book) {
        String sql = "UPDATE books SET title = ?, author = ?, isbn = ?, course = ?, " +
                     "subject = ?, price = ?, stock = ?, image_url = ?, description = ? " +
                     "WHERE id = ?";
        
        int rowsAffected = jdbcTemplate.update(sql,
            book.getTitle(),
            book.getAuthor(),
            book.getIsbn(),
            book.getCourse(),
            book.getSubject(),
            book.getPrice(),
            book.getStock(),
            book.getImageUrl(),
            book.getDescription(),
            book.getId()
        );
        return rowsAffected > 0;
    }
    
    /**
     * Deletes a book by ID
     * @param id The book ID to delete
     * @return true if deletion successful
     */
    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM books WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }
    
    /**
     * Counts total number of books
     * @return Total book count
     */
    @Override
    public int count() {
        String sql = "SELECT COUNT(*) FROM books";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count != null ? count : 0;
    }
    
    /**
     * Finds books by subject
     * @param subject The subject to filter by
     * @return List of books in that subject
     */
    @Override
    public List<BookModel> findBySubject(String subject) {
        String sql = "SELECT * FROM books WHERE subject = ? ORDER BY title";
        return jdbcTemplate.query(sql, bookRowMapper, subject);
    }
}