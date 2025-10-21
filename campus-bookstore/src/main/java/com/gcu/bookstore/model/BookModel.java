package com.gcu.bookstore.model;

public class BookModel {
    
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private String course; 
    private String subject; 
    private Double price;
    private Integer stock;
    private String imageUrl;
    private String description;
    
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
    public boolean matchesSearch(String query) {
        if (query == null || query.trim().isEmpty()) {
            return true; 
        }
        
        String lowerQuery = query.toLowerCase().trim();
        
        return (title != null && title.toLowerCase().contains(lowerQuery)) ||
               (author != null && author.toLowerCase().contains(lowerQuery)) ||
               (isbn != null && isbn.toLowerCase().contains(lowerQuery)) ||
               (course != null && course.toLowerCase().contains(lowerQuery)) ||
               (subject != null && subject.toLowerCase().contains(lowerQuery));
    }
}