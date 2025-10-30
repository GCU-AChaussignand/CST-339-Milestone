package com.gcu.bookstore.controller;

import com.gcu.bookstore.model.LoginCredentials;
import com.gcu.bookstore.model.User;
import com.gcu.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.util.Optional;

@Controller
public class LoginController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("credentials", new LoginCredentials());
        return "login";
    }
    
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("credentials") LoginCredentials credentials,
                       BindingResult result,
                       HttpSession session,
                       RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "login";
        }
        
        if (userService.authenticateUser(credentials.getUsername(), credentials.getPassword())) {
            Optional<User> user = userService.findByUsername(credentials.getUsername());
            if (user.isPresent()) {
                session.setAttribute("user", user.get());
                return "redirect:/";
            }
        }
        
        redirectAttributes.addFlashAttribute("error", "Invalid username or password");
        return "redirect:/login";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("message", "Logged out successfully!");
        return "redirect:/";
    }
}