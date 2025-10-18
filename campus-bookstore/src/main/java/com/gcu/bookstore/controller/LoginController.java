package com.gcu.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gcu.bookstore.model.LoginModel;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

/**
 * Login Controller
 * Handles user authentication with role-based redirection
 * Admin users -> Admin Dashboard
 * Regular users -> User Dashboard
 */
@Controller
public class LoginController {
    
    /**
     * Display login form
     */
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginModel", new LoginModel());
        return "login";
    }
    
    /**
     * Process login form submission
     * For Milestone 2: Simulates authentication without database
     * Admin credentials: admin/password -> redirects to admin dashboard
     * User credentials: user/password -> redirects to user dashboard
     */
    @PostMapping("/login")
    public String processLogin(@Valid LoginModel loginModel, 
                              BindingResult result, 
                              Model model,
                              HttpSession session) {
        
        // Check for validation errors
        if (result.hasErrors()) {
            return "login";
        }
        
        // Check if admin login
        if ("admin".equals(loginModel.getUsername()) && 
            "password".equals(loginModel.getPassword())) {
            
            // Store user info in session
            session.setAttribute("username", loginModel.getUsername());
            session.setAttribute("role", "ADMIN");
            session.setAttribute("isLoggedIn", true);
            
            // Redirect to admin dashboard
            return "redirect:/admin/dashboard";
        } 
        // Check if regular user login
        else if ("user".equals(loginModel.getUsername()) && 
                 "password".equals(loginModel.getPassword())) {
            
            // Store user info in session
            session.setAttribute("username", loginModel.getUsername());
            session.setAttribute("role", "USER");
            session.setAttribute("isLoggedIn", true);
            
            // Redirect to user dashboard
            return "redirect:/user/dashboard";
        } 
        else {
            // Failed login
            model.addAttribute("error", "Invalid username or password");
            model.addAttribute("loginModel", loginModel);
            return "login";
        }
    }
    
    /**
     * Logout handler
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }
}