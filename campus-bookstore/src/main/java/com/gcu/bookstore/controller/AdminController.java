package com.gcu.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @GetMapping("/dashboard")
    public String showDashboard() {
        return "admin-dashboard";
    }

    @GetMapping("/products")
    public String showProducts() {
        return "admin-products";
    }

    @GetMapping("/orders")
    public String showOrders() {
        return "admin-orders";
    }

    @GetMapping("/users")
    public String showUsers() {
        return "admin-users";
    }

    @GetMapping("/reports")
    public String showReports() {
        return "admin-reports";
    }

    @GetMapping("/settings")
    public String showSettings() {
        return "admin-settings";
    }
}