package com.gcu.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gcu.bookstore.model.UserModel;
import com.gcu.bookstore.service.UserService;

import jakarta.validation.Valid;


@Controller
public class RegistrationController {
    
    @Autowired
    private UserService userService;
    
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userModel", new UserModel());
        return "registration";
    }
    
    
    @PostMapping("/register")
    public String processRegistration(@Valid UserModel userModel, 
                                     BindingResult result, 
                                     Model model) {
        
        if (result.hasErrors()) {
            return "registration";
        }
        
        System.out.println("=== REGISTRATION ATTEMPT ===");
        System.out.println("Username: " + userModel.getUsername());
        System.out.println("Password: " + userModel.getPassword());
        System.out.println("First Name: " + userModel.getFirstName());
        System.out.println("Last Name: " + userModel.getLastName());
        System.out.println("Email: " + userModel.getEmail());
        
        if (userService.usernameExists(userModel.getUsername())) {
            System.out.println("ERROR: Username already exists!");
            model.addAttribute("error", "Username already exists. Please choose another.");
            return "registration";
        }
        
        boolean success = userService.registerUser(userModel);
        System.out.println("Registration success: " + success);
        
        System.out.println("Total users now: " + userService.getTotalUsers());
        
        if (success) {
            return "redirect:/login?registered";
        } else {
            model.addAttribute("error", "Registration failed. Please try again.");
            return "registration";
        }
    }
}