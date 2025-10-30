package com.gcu.bookstore.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gcu.bookstore.model.Book;
import com.gcu.bookstore.model.CartItem;
import com.gcu.bookstore.model.User;
import com.gcu.bookstore.repository.CartItemRepository;

@Service
@Transactional
public class CartService {
    
    @Autowired
    private CartItemRepository cartItemRepository;
    
    public List<CartItem> getCartItems(User user) {
        return cartItemRepository.findByUser(user);
    }
    
    public void addToCart(User user, Book book, Integer quantity) {
        Optional<CartItem> existingItem = cartItemRepository.findByUserAndBook(user, book);
        
        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
            cartItemRepository.save(item);
        } else {
            CartItem newItem = new CartItem(user, book, quantity);
            cartItemRepository.save(newItem);
        }
    }
    
    public void updateQuantity(Long cartItemId, Integer quantity) {
        Optional<CartItem> item = cartItemRepository.findById(cartItemId);
        if (item.isPresent()) {
            item.get().setQuantity(quantity);
            cartItemRepository.save(item.get());
        }
    }
    
    public void removeItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }
    
    public void clearCart(User user) {
        cartItemRepository.deleteByUser(user);
    }
    
    public BigDecimal calculateSubtotal(List<CartItem> items) {
        return items.stream()
            .map(CartItem::getSubtotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    public BigDecimal calculateTax(BigDecimal subtotal) {
        return subtotal.multiply(new BigDecimal("0.08"));
    }
    
    public BigDecimal calculateTotal(BigDecimal subtotal, BigDecimal tax, BigDecimal shipping) {
        return subtotal.add(tax).add(shipping);
    }
}