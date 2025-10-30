package com.gcu.bookstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gcu.bookstore.model.BookModel;

/**
 * Book Service
 * Handles book-related business logic
 * For Milestone 2: Uses in-memory ArrayList
 * Will be refactored to use database in Milestone 4
 */
@Service
public class BookService {
    
    private final List<BookModel> books;
    
    /**
     * Constructor - Initialize with sample data
     */
    public BookService() {
        books = new ArrayList<>();
        initializeSampleData();
    }
    
    /**
     * Initialize sample book data for testing
     */
    private void initializeSampleData() {
        books.add(new BookModel(
            1L,
            "Introduction to Algorithms",
            "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest",
            "978-0262033848",
            "CST-339",
            "Computer Science",
            89.99,
            25,
            null,
            "Comprehensive guide to algorithms and data structures"
        ));
        
        books.add(new BookModel(
            2L,
            "Calculus Early Transcendentals",
            "James Stewart",
            "978-1285741550",
            "MATH-210",
            "Mathematics",
            120.00,
            18,
            null,
            "Complete calculus textbook with examples"
        ));
        
        books.add(new BookModel(
            3L,
            "Chemistry: The Central Science",
            "Theodore E. Brown, H. Eugene LeMay",
            "978-0134414232",
            "CHEM-101",
            "Chemistry",
            95.00,
            8,
            null,
            "Fundamental chemistry concepts and applications"
        ));
        
        books.add(new BookModel(
            4L,
            "Introduction to Psychology",
            "James W. Kalat",
            "978-1305271555",
            "PSY-101",
            "Psychology",
            75.99,
            30,
            null,
            "Introduction to psychological concepts and research"
        ));
        
        books.add(new BookModel(
            5L,
            "Java: How to Program",
            "Paul Deitel, Harvey Deitel",
            "978-0134743356",
            "CST-239",
            "Computer Science",
            110.00,
            15,
            null,
            "Complete Java programming guide with examples"
        ));
        
        books.add(new BookModel(
            6L,
            "Database System Concepts",
            "Abraham Silberschatz, Henry F. Korth",
            "978-0078022159",
            "CST-345",
            "Computer Science",
            95.50,
            12,
            null,
            "Database design and implementation fundamentals"
        ));
        
        books.add(new BookModel(
            7L,
            "Physics for Scientists and Engineers",
            "Raymond A. Serway, John W. Jewett",
            "978-1285737027",
            "PHY-201",
            "Physics",
            135.00,
            10,
            null,
            "Comprehensive physics textbook with applications"
        ));
        
        books.add(new BookModel(
            8L,
            "Principles of Economics",
            "N. Gregory Mankiw",
            "978-0538453059",
            "ECON-101",
            "Economics",
            89.00,
            20,
            null,
            "Introduction to microeconomics and macroeconomics"
        ));
        
        books.add(new BookModel(
            9L,
            "Operating System Concepts",
            "Abraham Silberschatz, Peter B. Galvin",
            "978-1118063330",
            "CST-340",
            "Computer Science",
            105.99,
            14,
            null,
            "Operating systems principles and design"
        ));
        
        books.add(new BookModel(
            10L,
            "Statistics for Business and Economics",
            "Paul Newbold, William L. Carlson",
            "978-0134506593",
            "STAT-250",
            "Statistics",
            98.50,
            16,
            null,
            "Statistical methods for business analysis"
        ));
    }
    
    /**
     * Get all books
     */
    public List<BookModel> getAllBooks() {
        return new ArrayList<>(books);
    }
    
    /**
     * Search books by query
     * Searches across: title, author, ISBN, course, subject
     */
    public List<BookModel> searchBooks(String query) {
        if (query == null || query.trim().isEmpty()) {
            return getAllBooks();
        }
        
        return books.stream()
                .filter(book -> book.matchesSearch(query))
                .collect(Collectors.toList());
    }
    
    /**
     * Get book by ID
     */
    public BookModel getBookById(Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Get total number of books
     */
    public int getTotalBooks() {
        return books.size();
    }
    
    /**
     * Add a new book to the system
     * For Milestone 3: Adds to in-memory list
     * Will be replaced with database insert in Milestone 4
     * 
     * @param book The book to add
     * @return true if successful, false otherwise
     */
    public boolean addBook(BookModel book) {
        try {
            // Generate new ID
            Long newId = books.stream()
                    .mapToLong(BookModel::getId)
                    .max()
                    .orElse(0L) + 1;
            
            book.setId(newId);
            books.add(book);
            
            System.out.println("Book added successfully! Total books: " + books.size());
            return true;
        } catch (Exception e) {
            System.err.println("Error adding book: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Update an existing book
     * For Milestone 3: Updates in-memory list
     * 
     * @param book The book with updated information
     * @return true if successful, false otherwise
     */
    public boolean updateBook(BookModel book) {
        try {
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).getId().equals(book.getId())) {
                    books.set(i, book);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            System.err.println("Error updating book: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Delete a book by ID
     * For Milestone 3: Removes from in-memory list
     * 
     * @param id The ID of the book to delete
     * @return true if successful, false otherwise
     */
    public boolean deleteBook(Long id) {
        try {
            return books.removeIf(book -> book.getId().equals(id));
        } catch (Exception e) {
            System.err.println("Error deleting book: " + e.getMessage());
            return false;
        }
    }
}