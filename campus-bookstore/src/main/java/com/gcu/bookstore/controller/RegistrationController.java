package com.gcu.bookstore.controller;

import com.gcu.bookstore.model.User;
import com.gcu.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;

@Controller
public class RegistrationController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute User user,
                          BindingResult result,
                          RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "register";
        }
        
        if (userService.usernameExists(user.getUsername())) {
            result.rejectValue("username", "error.user", "Username already exists");
            return "register";
        }
        
        if (userService.emailExists(user.getEmail())) {
            result.rejectValue("email", "error.user", "Email already exists");
            return "register";
        }
        
        userService.registerUser(user);
        redirectAttributes.addFlashAttribute("message", "Registration successful! Please login.");
        return "redirect:/login";
    }
}