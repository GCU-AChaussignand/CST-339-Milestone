package com.gcu.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcu.bookstore.model.BookModel;
import com.gcu.bookstore.service.BookService;

/**
 * Page Controller
 * Handles navigation for public pages
 */
@Controller
public class PageController {
    
    @Autowired
    private BookService bookService;
    
    /**
     * Display home page
     */
    @GetMapping("/")
    public String home() {
        return "home";
    }
    
    /**
     * Display products page with optional search
     */
    @GetMapping("/products")
    public String products(@RequestParam(required = false) String search, Model model) {
        List<BookModel> books;
        
        if (search != null && !search.trim().isEmpty()) {
            books = bookService.searchBooks(search);
            model.addAttribute("searchQuery", search);
            model.addAttribute("searchPerformed", true);
        } else {
            books = bookService.getAllBooks();
            model.addAttribute("searchPerformed", false);
        }
        
        model.addAttribute("books", books);
        model.addAttribute("resultCount", books.size());
        return "products";
    }
    
    /**
     * Handle search from home page
     */
    @GetMapping("/search")
    public String search(@RequestParam String query) {
        // Redirect to products page with search parameter
        return "redirect:/products?search=" + query;
    }
    
    /**
     * Display cart page
     */
    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }
    
    /**
     * Display checkout page
     */
    @GetMapping("/checkout")
    public String checkout() {
        return "checkout";
    }
}