package com.gcu.bookstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gcu.bookstore.model.Book;
import com.gcu.bookstore.model.CartItem;
import com.gcu.bookstore.model.User;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);
    Optional<CartItem> findByUserAndBook(User user, Book book);
    void deleteByUser(User user);
}