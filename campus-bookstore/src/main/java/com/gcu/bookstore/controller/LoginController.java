package com.gcu.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gcu.bookstore.model.LoginModel;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class LoginController {
 
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
        
        if ("admin".equals(loginModel.getUsername()) && 
            "password".equals(loginModel.getPassword())) {
            
            session.setAttribute("username", loginModel.getUsername());
            session.setAttribute("role", "ADMIN");
            session.setAttribute("isLoggedIn", true);
            
            return "redirect:/admin/dashboard";
        } 

        else if ("user".equals(loginModel.getUsername()) && 
                 "password".equals(loginModel.getPassword())) {
            
            session.setAttribute("username", loginModel.getUsername());
            session.setAttribute("role", "USER");
            session.setAttribute("isLoggedIn", true);
            
            return "redirect:/user/dashboard";
        } 
        else {
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