package com.gcu.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

/**
 * Controller for handling user dashboard and profile pages.
 * Ensures proper authentication and authorization before accessing user pages.
 * 
 * @author Campus Bookstore Team
 * @version 2.0 - Milestone 4 (Enhanced with better security)
 * @since 2025-10-28
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * Displays the user dashboard with account overview.
     * Redirects to login if user is not authenticated.
     * 
     * @param session HTTP session to check authentication status
     * @param model Model to pass data to the view
     * @return View name or redirect URL
     */
    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        // Check if user is logged in
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");

        if (isLoggedIn == null || !isLoggedIn || username == null) {
            System.out.println("Unauthorized access attempt to user dashboard - redirecting to login");
            return "redirect:/login";
        }

        // Prevent admin from accessing user dashboard (they should use admin dashboard)
        if ("ADMIN".equalsIgnoreCase(role)) {
            System.out.println("Admin trying to access user dashboard - redirecting to admin dashboard");
            return "redirect:/admin/dashboard";
        }

        System.out.println("User dashboard accessed by: " + username);
        
        // Add user information to model
        model.addAttribute("username", username);
        model.addAttribute("firstName", session.getAttribute("firstName"));
        model.addAttribute("lastName", session.getAttribute("lastName"));
        model.addAttribute("email", session.getAttribute("email"));
        
        return "user-dashboard";
    }

    /**
     * Displays the user profile page for editing account information.
     * Redirects to login if user is not authenticated.
     * 
     * @param session HTTP session to check authentication status
     * @param model Model to pass data to the view
     * @return View name or redirect URL
     */
    @GetMapping("/profile")
    public String showProfile(HttpSession session, Model model) {
        // Check if user is logged in
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        String username = (String) session.getAttribute("username");

        if (isLoggedIn == null || !isLoggedIn || username == null) {
            System.out.println("Unauthorized access attempt to profile - redirecting to login");
            return "redirect:/login";
        }

        System.out.println("Profile page accessed by: " + username);
        
        model.addAttribute("username", username);
        model.addAttribute("firstName", session.getAttribute("firstName"));
        model.addAttribute("lastName", session.getAttribute("lastName"));
        model.addAttribute("email", session.getAttribute("email"));
        model.addAttribute("phoneNumber", session.getAttribute("phoneNumber"));
        
        return "user-profile";
    }

    /**
     * Displays the user's order history.
     * Redirects to login if user is not authenticated.
     * 
     * @param session HTTP session to check authentication status
     * @param model Model to pass data to the view
     * @return View name or redirect URL
     */
    @GetMapping("/orders")
    public String showOrders(HttpSession session, Model model) {
        // Check if user is logged in
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        String username = (String) session.getAttribute("username");

        if (isLoggedIn == null || !isLoggedIn || username == null) {
            System.out.println("Unauthorized access attempt to orders - redirecting to login");
            return "redirect:/login";
        }

        System.out.println("Orders page accessed by: " + username);
        
        model.addAttribute("username", username);
        
        
        // List<Order> orders = orderService.getOrdersByUsername(username);
        // model.addAttribute("orders", orders);
        
        return "user-orders";
    }
}