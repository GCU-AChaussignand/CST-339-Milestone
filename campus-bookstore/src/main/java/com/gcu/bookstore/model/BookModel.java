package com.gcu.bookstore.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Book Model
 * Represents a book/textbook in the bookstore
 * For Milestone 2-3: Used for in-memory data
 * Will be connected to database in Milestone 4
 * 
 * @author Campus Bookstore Team
 * @version 1.0
 */
public class BookModel {
    
    private Long id;
    
    @NotEmpty(message = "Title is required")
    @Size(min = 2, max = 200, message = "Title must be between 2 and 200 characters")
    private String title;
    
    @NotEmpty(message = "Author is required")
    @Size(min = 2, max = 200, message = "Author must be between 2 and 200 characters")
    private String author;
    
    @NotEmpty(message = "ISBN is required")
    @Size(min = 10, max = 17, message = "ISBN must be between 10 and 17 characters")
    private String isbn;
    
    @NotEmpty(message = "Course code is required")
    @Size(min = 3, max = 20, message = "Course code must be between 3 and 20 characters")
    private String course;
    
    @NotEmpty(message = "Subject is required")
    @Size(min = 2, max = 50, message = "Subject must be between 2 and 50 characters")
    private String subject;
    
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private Double price;
    
    @NotNull(message = "Stock quantity is required")
    @Min(value = 0, message = "Stock cannot be negative")
    private Integer stock;
    
    private String imageUrl;
    private String description;
    
    // Constructors
    public BookModel() {
    }
    
    public BookModel(Long id, String title, String author, String isbn, String course, 
                     String subject, Double price, Integer stock, String imageUrl, String description) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.course = course;
        this.subject = subject;
        this.price = price;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.description = description;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public String getCourse() {
        return course;
    }
    
    public void setCourse(String course) {
        this.course = course;
    }
    
    public String getSubject() {
        return subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public Double getPrice() {
        return price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
    
    public Integer getStock() {
        return stock;
    }
    
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Helper method to check if this book matches a search query
     * Searches across: title, author, ISBN, course, and subject
     */
    public boolean matchesSearch(String query) {
        if (query == null || query.trim().isEmpty()) {
            return true; // No search query, show all
        }
        
        String lowerQuery = query.toLowerCase().trim();
        
        return (title != null && title.toLowerCase().contains(lowerQuery)) ||
               (author != null && author.toLowerCase().contains(lowerQuery)) ||
               (isbn != null && isbn.toLowerCase().contains(lowerQuery)) ||
               (course != null && course.toLowerCase().contains(lowerQuery)) ||
               (subject != null && subject.toLowerCase().contains(lowerQuery));
    }
}