package com.gcu.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gcu.bookstore.model.LoginModel;
import com.gcu.bookstore.model.UserModel;
import com.gcu.bookstore.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class LoginController {
    
    @Autowired
    private UserService userService;
    
    
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginModel", new LoginModel());
        return "login";
    }
    
    
    @PostMapping("/login")
    public String processLogin(@Valid LoginModel loginModel, 
                              BindingResult result, 
                              Model model,
                              HttpSession session) {
        
        if (result.hasErrors()) {
            return "login";
        }
        
        System.out.println("=== LOGIN ATTEMPT ===");
        System.out.println("Trying to login with username: " + loginModel.getUsername());
        System.out.println("Password length: " + loginModel.getPassword().length());
        System.out.println("Total users in system: " + userService.getTotalUsers());
        
        UserModel user = userService.authenticateUser(
            loginModel.getUsername(), 
            loginModel.getPassword()
        );
        
        if (user != null) {
            System.out.println("LOGIN SUCCESS for: " + user.getUsername());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("firstName", user.getFirstName());
            session.setAttribute("lastName", user.getLastName());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("isLoggedIn", true);
            
            if (userService.isAdmin(user.getUsername())) {
                session.setAttribute("role", "ADMIN");
                return "redirect:/admin/dashboard";
            } else {
                session.setAttribute("role", "USER");
                return "redirect:/user/dashboard";
            }
        } else {
            System.out.println("LOGIN FAILED - Invalid credentials");
            model.addAttribute("error", "Invalid username or password");
            model.addAttribute("loginModel", loginModel);
            return "login";
        }
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }
}