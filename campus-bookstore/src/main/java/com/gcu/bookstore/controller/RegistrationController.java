package com.gcu.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gcu.bookstore.model.User;
import com.gcu.bookstore.service.UserService;

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
    public String processRegistration(@Valid @ModelAttribute("user") User user,
                                      BindingResult result,
                                      Model model) {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            result.rejectValue("confirmPassword", "password.mismatch", "Passwords do not match");
        }

        if (result.hasErrors()) {
            return "register";
        }

        boolean success = userService.register(user);
        if (!success) {
            model.addAttribute("errorMessage", "Username already exists");
            return "register";
        }

        model.addAttribute("successMessage", "Registration successful. Please login.");
        model.addAttribute("user", new User());
        return "register";
    }
}
