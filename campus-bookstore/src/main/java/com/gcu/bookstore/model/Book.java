package com.gcu.bookstore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "books")
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Title is required")
    @Column(nullable = false)
    private String title;
    
    @NotBlank(message = "Author is required")
    @Column(nullable = false)
    private String author;
    
    @NotBlank(message = "ISBN is required")
    @Column(unique = true, nullable = false)
    private String isbn;
    
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    
    @Min(value = 0, message = "Stock cannot be negative")
    @Column(nullable = false)
    private Integer stock = 0;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    // FIXED: Removed name="image_url" to avoid duplicate mapping
    // Hibernate will automatically map imageUrl -> image_url
    @Column(length = 500)
    private String imageUrl;
    
    // Optional: Add course and subject fields to match BookModel
    @Column(length = 50)
    private String course;
    
    @Column(length = 100)
    private String subject;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    
    // Constructors
    public Book() {}
    
    public Book(String title, String author, String isbn, BigDecimal price, Integer stock) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
        this.stock = stock;
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
    
    public BigDecimal getPrice() { 
        return price; 
    }
    
    public void setPrice(BigDecimal price) { 
        this.price = price; 
    }
    
    public Integer getStock() { 
        return stock; 
    }
    
    public void setStock(Integer stock) { 
        this.stock = stock; 
    }
    
    public String getDescription() { 
        return description; 
    }
    
    public void setDescription(String description) { 
        this.description = description; 
    }
    
    public String getImageUrl() { 
        return imageUrl; 
    }
    
    public void setImageUrl(String imageUrl) { 
        this.imageUrl = imageUrl; 
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
    
    public LocalDateTime getCreatedAt() { 
        return createdAt; 
    }
    
    public void setCreatedAt(LocalDateTime createdAt) { 
        this.createdAt = createdAt; 
    }
}