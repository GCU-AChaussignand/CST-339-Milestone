package com.gcu.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gcu.bookstore.model.LoginForm; // ✅ correct for Spring Boot 3.x
import com.gcu.bookstore.service.UserService;

import jakarta.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLogin(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@Valid @ModelAttribute("loginForm") LoginForm form,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            return "login";
        }

        boolean authenticated = userService.authenticate(form.getUsername(), form.getPassword());
        if (!authenticated) {
            model.addAttribute("errorMessage", "Invalid username or password");
            return "login";
        }


        model.addAttribute("username", form.getUsername());
        return "products";
    }
}
