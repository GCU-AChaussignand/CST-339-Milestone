package com.gcu.bookstore.repository;

import com.gcu.bookstore.model.BookModel;
import java.util.List;

/**
 * Repository interface for Book data access operations
 * Defines contract for CRUD operations on Book entities
 * 
 * @author [Your Names]
 * @version 1.0
 * @since 2025-10-30
 */
public interface BookRepository {
    
    /**
     * Saves a new book to the database
     * @param book The book to save
     * @return The saved book with generated ID
     */
    BookModel save(BookModel book);
    
    /**
     * Finds a book by ID
     * @param id The book ID
     * @return The book if found, null otherwise
     */
    BookModel findById(Long id);
    
    /**
     * Finds a book by ISBN
     * @param isbn The ISBN to search for
     * @return The book if found, null otherwise
     */
    BookModel findByIsbn(String isbn);
    
    /**
     * Retrieves all books from the database
     * @return List of all books
     */
    List<BookModel> findAll();
    
    /**
     * Searches books by query string
     * @param query Search term to match against title, author, ISBN, course, or subject
     * @return List of matching books
     */
    List<BookModel> search(String query);
    
    /**
     * Updates an existing book
     * @param book The book with updated information
     * @return true if update successful
     */
    boolean update(BookModel book);
    
    /**
     * Deletes a book by ID
     * @param id The book ID to delete
     * @return true if deletion successful
     */
    boolean deleteById(Long id);
    
    /**
     * Counts total number of books
     * @return Total book count
     */
    int count();
    
    /**
     * Finds books by subject
     * @param subject The subject to filter by
     * @return List of books in that subject
     */
    List<BookModel> findBySubject(String subject);
}