package com.gcu.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    
    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        
        if (username == null) {
            return "redirect:/login";
        }
        
        model.addAttribute("username", username);
        return "user-dashboard";
    }

    @GetMapping("/profile")
    public String showProfile(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        
        if (username == null) {
            return "redirect:/login";
        }
        
        model.addAttribute("username", username);
        return "user-profile";
    }

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