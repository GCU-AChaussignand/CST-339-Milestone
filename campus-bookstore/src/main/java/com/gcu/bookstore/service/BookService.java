package com.gcu.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.bookstore.model.BookModel;
import com.gcu.bookstore.repository.BookRepository;

/**
 * Service class for book-related business logic
 * Acts as intermediary between controllers and repository
 * Contains business rules and validation
 * 
 * @author [Your Names]
 * @version 1.0
 * @since 2025-10-30
 */
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    /**
     * Retrieves all books from the database
     * 
     * @return List of all books
     */
    public List<BookModel> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Searches for books matching the query string
     * Searches across title, author, ISBN, course, and subject
     * 
     * @param query The search query
     * @return List of matching books
     */
    public List<BookModel> searchBooks(String query) {
        if (query == null || query.trim().isEmpty()) {
            return getAllBooks();
        }
        return bookRepository.search(query.trim());
    }

    /**
     * Retrieves a book by its ID
     * 
     * @param id The book ID
     * @return BookModel if found, null otherwise
     */
    public BookModel getBookById(Long id) {
        return bookRepository.findById(id);
    }

    /**
     * Gets the total count of books in the system
     * 
     * @return Total number of books
     */
    public int getTotalBooks() {
        return bookRepository.count();
    }

    /**
     * Saves a new book to the database
     * Validates required fields before saving
     * 
     * @param book The book to save
     * @return The saved book with generated ID
     * @throws IllegalArgumentException if validation fails
     */
    public BookModel saveBook(BookModel book) {
        // Validate required fields
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Book title is required");
        }
        if (book.getPrice() == null || book.getPrice() <= 0) {
            throw new IllegalArgumentException("Valid book price is required");
        }

        return bookRepository.save(book);
    }

    /**
     * Updates an existing book in the database
     * 
     * @param book The book with updated information
     * @return true if update successful, false otherwise
     */
    public boolean updateBook(BookModel book) {
        // Validate book exists
        if (book.getId() == null || bookRepository.findById(book.getId()) == null) {
            throw new IllegalArgumentException("Book not found");
        }
        
        return bookRepository.update(book);
    }

    /**
     * Deletes a book from the database
     * 
     * @param id The ID of the book to delete
     * @return true if deletion successful, false otherwise
     */
    public boolean deleteBook(Long id) {
        return bookRepository.deleteById(id);
    }

    /**
     * Retrieves books by subject category
     * 
     * @param subject The subject to filter by
     * @return List of books in that subject
     */
    public List<BookModel> getBooksBySubject(String subject) {
        return bookRepository.findBySubject(subject);
    }
}