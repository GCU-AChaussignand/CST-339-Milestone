package com.gcu.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Admin Controller
 * Handles admin dashboard and management pages
 * Note: For Milestone 2, this is a basic implementation
 * Security will be added in Milestone 6
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    
    /**
     * Display admin dashboard with overview stats
     */
    @GetMapping("/dashboard")
    public String showDashboard() {
        return "admin-dashboard";
    }
    
    /**
     * Display products management page
     */
    @GetMapping("/products")
    public String showProducts() {
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
    public String showUsers() {
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
    public String showSettings() {
        return "admin-settings";
    }
}