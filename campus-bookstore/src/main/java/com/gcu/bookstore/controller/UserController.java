package com.gcu.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

/**
 * User Controller
 * Handles user dashboard and profile pages for regular users
 */
@Controller
@RequestMapping("/user")
public class UserController {
    
    /**
     * Display user dashboard with their info and orders
     */
    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        // Get user info from session
        String username = (String) session.getAttribute("username");
        
        if (username == null) {
            return "redirect:/login";
        }
        
        model.addAttribute("username", username);
        return "user-dashboard";
    }
    
    /**
     * Display user profile page
     */
    @GetMapping("/profile")
    public String showProfile(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        
        if (username == null) {
            return "redirect:/login";
        }
        
        model.addAttribute("username", username);
        return "user-profile";
    }
    
    /**
     * Display user orders history
     */
    @GetMapping("/orders")
    public String showOrders(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        
        if (username == null) {
            return "redirect:/login";
        }
        
        model.addAttribute("username", username);
        return "user-orders";
    }
}