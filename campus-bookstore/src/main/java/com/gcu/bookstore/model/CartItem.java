package com.gcu.bookstore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cart_items")
public class CartItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
    
    @Min(value = 1, message = "Quantity must be at least 1")
    @Column(nullable = false)
    private Integer quantity = 1;
    
    @Column(name = "added_at", nullable = false)
    private LocalDateTime addedAt = LocalDateTime.now();
    
    public CartItem() {}
    
    public CartItem(User user, Book book, Integer quantity) {
        this.user = user;
        this.book = book;
        this.quantity = quantity;
    }
    
    public BigDecimal getSubtotal() {
        return book.getPrice().multiply(new BigDecimal(quantity));
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
    
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    
    public LocalDateTime getAddedAt() { return addedAt; }
    public void setAddedAt(LocalDateTime addedAt) { this.addedAt = addedAt; }
}