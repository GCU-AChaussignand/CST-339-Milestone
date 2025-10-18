package com.gcu.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gcu.bookstore.model.UserModel;

import jakarta.validation.Valid;

/**
 * Registration Controller
 * Handles user registration (without database for Milestone 2)
 */
@Controller
public class RegistrationController {
    
    /**
     * Display registration form
     * IMPORTANT: Must add empty UserModel to the model
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userModel", new UserModel());
        return "registration";
    }
    
    /**
     * Process registration form submission
     * For Milestone 2: Validates and displays success without database
     */
    @PostMapping("/register")
    public String processRegistration(@Valid UserModel userModel, 
                                     BindingResult result, 
                                     Model model) {
        
        // Check for validation errors
        if (result.hasErrors()) {
            return "registration";
        }
        
        // Simulate successful registration
        // Redirect to login page with success message
        return "redirect:/login?registered";
    }
}