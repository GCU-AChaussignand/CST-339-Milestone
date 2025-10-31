package com.gcu.bookstore.controller;

import com.gcu.bookstore.model.BookModel;
import com.gcu.bookstore.service.BookService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Product Controller
 * Handles product/book creation, update, and management
 * Uses Spring Beans and Dependency Injection for business services
 * 
 * @author Campus Bookstore Team
 * @version 1.0
 * @since Milestone 3
 */
@Controller
@RequestMapping("/products")
public class ProductController {
    
    /**
     * Book Service - Injected via Spring DI
     * Handles all business logic for book management
     */
    @Autowired
    private BookService bookService;
    
    /**
     * Display product creation form
     * Requires admin authentication
     * 
     * @param model Spring Model for passing data to view
     * @param session HTTP Session to check authentication
     * @return String view name for product creation form
     */
    @GetMapping("/create")
    public String showCreateForm(Model model, HttpSession session) {
        // Check if user is logged in and is admin
        String role = (String) session.getAttribute("role");
        if (role == null || !"ADMIN".equals(role)) {
            return "redirect:/login";
        }
        
        // Add empty BookModel to form
        model.addAttribute("bookModel", new BookModel());
        model.addAttribute("mode", "create");
        return "product-form";
    }
    
    /**
     * Process product creation form submission
     * Validates input and adds product to system
     * For Milestone 3: Uses in-memory storage via BookService
     * Will be persisted to database in Milestone 4
     * 
     * @param bookModel The book data from form
     * @param result Validation results
     * @param model Spring Model
     * @param session HTTP Session
     * @return String redirect path or view name
     */
    @PostMapping("/create")
public String processCreate(@Valid BookModel bookModel, 
                           BindingResult result,
                           Model model,
                           HttpSession session) {
    // Check authentication
    String role = (String) session.getAttribute("role");
    if (role == null || !"ADMIN".equals(role)) {
        return "redirect:/login";
    }
    
    // Check for validation errors
    if (result.hasErrors()) {
        model.addAttribute("mode", "create");
        return "product-form";
    }
    
    // Add product via service
    try {
        bookService.saveBook(bookModel);
        return "redirect:/admin/products?created";
    } catch (IllegalArgumentException e) {
        model.addAttribute("error", "Failed to create product: " + e.getMessage());
        model.addAttribute("mode", "create");
        return "product-form";
    }
}
}