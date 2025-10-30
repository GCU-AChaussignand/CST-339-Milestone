package com.gcu.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcu.bookstore.model.BookModel;
import com.gcu.bookstore.service.BookService;
import com.gcu.bookstore.util.BookConverter;

import jakarta.servlet.http.HttpSession;

/**
 * Controller for handling public pages (home, products, cart, checkout).
 * Manages navigation and data display for non-authenticated and authenticated users.
 * 
 * @author Campus Bookstore Team
 * @version 2.0 - Milestone 4 (Enhanced with session management)
 * @since 2025-10-28
 */
@Controller
public class PageController {

    /**
     * Book service for accessing book data.
     */
    @Autowired
    private BookService bookService;

    /**
     * Displays the home page.
     * 
     * @param session HTTP session to check user status
     * @param model Model to pass data to view
     * @return View name
     */
    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        return "home";
    }

    /**
     * Displays the products page with optional search functionality.
     * 
     * @param search Optional search query parameter
     * @param session HTTP session to check user status
     * @param model Model to pass data to view
     * @return View name
     */
    @GetMapping("/products")
    public String products(@RequestParam(required = false) String search, 
                          HttpSession session, 
                          Model model) {
        List<BookModel> books;

        // Add user info to model
        addUserInfoToModel(session, model);

        // Search or get all books - Convert Book entities to BookModel DTOs
        if (search != null && !search.trim().isEmpty()) {
            books = BookConverter.toBookModelList(bookService.searchBooks(search));
            model.addAttribute("searchQuery", search);
            model.addAttribute("searchPerformed", true);
            System.out.println("Products page - Search performed: " + search + " - Results: " + books.size());
        } else {
            books = BookConverter.toBookModelList(bookService.getAllBooks());
            model.addAttribute("searchPerformed", false);
            System.out.println("Products page - Displaying all books: " + books.size());
        }

        model.addAttribute("books", books);
        model.addAttribute("resultCount", books.size());
        
        return "products";
    }

    /**
     * Handles search requests and redirects to products page with search parameter.
     * 
     * @param query Search query
     * @return Redirect URL
     */
    @GetMapping("/search")
    public String search(@RequestParam String query) {
        System.out.println("Search initiated with query: " + query);
        return "redirect:/products?search=" + query;
    }

    /**
     * Displays the shopping cart page.
     * Adds user session information to properly identify the user's cart.
     * 
     * @param session HTTP session to check user status
     * @param model Model to pass data to view
     * @return View name
     */
    @GetMapping("/cart")
    public String cart(HttpSession session, Model model) {
        // Add user info to model
        addUserInfoToModel(session, model);
        
        // Check if user is logged in
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        String username = (String) session.getAttribute("username");
        
        if (isLoggedIn != null && isLoggedIn && username != null) {
            System.out.println("Cart accessed by logged-in user: " + username);
            model.addAttribute("userLoggedIn", true);
        } else {
            System.out.println("Cart accessed by guest user");
            model.addAttribute("userLoggedIn", false);
        }
        
        
        // if (username != null) {
        //     List<CartItem> cartItems = cartService.getCartItems(username);
        //     model.addAttribute("cartItems", cartItems);
        // }
        
        return "cart";
    }

    /**
     * Displays the checkout page.
     * Requires user to be logged in (redirects to login if not).
     * 
     * @param session HTTP session to check authentication
     * @param model Model to pass data to view
     * @return View name or redirect URL
     */
    @GetMapping("/checkout")
    public String checkout(HttpSession session, Model model) {
        // Check if user is logged in
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        String username = (String) session.getAttribute("username");
        
        if (isLoggedIn == null || !isLoggedIn || username == null) {
            System.out.println("Checkout attempted without login - redirecting");
            // Save the intended destination in session
            session.setAttribute("redirectAfterLogin", "/checkout");
            return "redirect:/login";
        }
        
        System.out.println("Checkout accessed by: " + username);
        
        // Add user info to model
        addUserInfoToModel(session, model);
        
        // Pre-fill checkout form with user information
        model.addAttribute("firstName", session.getAttribute("firstName"));
        model.addAttribute("lastName", session.getAttribute("lastName"));
        model.addAttribute("email", session.getAttribute("email"));
        model.addAttribute("phoneNumber", session.getAttribute("phoneNumber"));
        
        
        // List<CartItem> cartItems = cartService.getCartItems(username);
        // model.addAttribute("cartItems", cartItems);
        // model.addAttribute("cartTotal", cartService.calculateTotal(cartItems));
        
        return "checkout";
    }

    /**
     * Helper method to add user information to the model if logged in.
     * This makes user info available to all views.
     * 
     * @param session HTTP session containing user data
     * @param model Model to add attributes to
     */
    private void addUserInfoToModel(HttpSession session, Model model) {
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        
        if (isLoggedIn != null && isLoggedIn) {
            model.addAttribute("isLoggedIn", true);
            model.addAttribute("username", session.getAttribute("username"));
            model.addAttribute("firstName", session.getAttribute("firstName"));
            model.addAttribute("role", session.getAttribute("role"));
        } else {
            model.addAttribute("isLoggedIn", false);
        }
    }
}