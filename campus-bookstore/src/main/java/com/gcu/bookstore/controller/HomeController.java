package com.gcu.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        model.addAttribute("title", "Campus Bookstore");
        return "Home/Home";
    }

    @GetMapping("/products")
    public String products() {
        return "products";
    }
}
