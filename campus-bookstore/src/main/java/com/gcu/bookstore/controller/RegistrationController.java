package com.gcu.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gcu.bookstore.model.UserModel;

import jakarta.validation.Valid;

@Controller
public class RegistrationController {
    
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
        return "redirect:/login?registered";
    }
}