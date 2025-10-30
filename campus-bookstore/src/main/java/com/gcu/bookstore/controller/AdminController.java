package com.gcu.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.bookstore.service.BookService;
import com.gcu.bookstore.service.UserService;

/**
 * Admin Controller
 * Handles admin dashboard and management pages
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private BookService bookService;
    
    @Autowired
    private UserService userService;
    
    /**
     * Display admin dashboard with overview stats
     */
    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("totalBooks", bookService.getTotalBooks());
        model.addAttribute("totalUsers", userService.getTotalUsers());
        return "admin-dashboard";
    }
    
    /**
     * Display products management page
     */
    @GetMapping("/products")
    public String showProducts(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "admin-products";
    }
    
    /**
     * Display orders management page
     */
    @GetMapping("/orders")
    public String showOrders() {
        return "admin-orders";
    }
    
    /**
     * Display users management page
     */
    @GetMapping("/users")
    public String showUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("totalUsers", userService.getTotalUsers());
        return "admin-users";
    }
    
    /**
     * Display reports page
     */
    @GetMapping("/reports")
    public String showReports() {
        return "admin-reports";
    }
    
    /**
     * Display settings page
     */
    @GetMapping("/settings")
    public String showSettings(Model model) {
        model.addAttribute("totalBooks", bookService.getTotalBooks());
        model.addAttribute("totalUsers", userService.getTotalUsers());
        return "admin-settings";
    }
}