package com.gcu.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.bookstore.model.Book;
import com.gcu.bookstore.repository.BookRepository;

import jakarta.servlet.http.HttpSession;

/**
 * Controller for handling admin dashboard and management pages.
 * Ensures only users with ADMIN role can access these pages.
 * 
 * @author Campus Bookstore Team
 * @version 2.0 - Milestone 4 (Enhanced with security checks)
 * @since 2025-10-28
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    /**
     * Checks if the current user has admin privileges.
     * 
     * @param session HTTP session containing user information
     * @return true if user is admin, false otherwise
     */
    private boolean isAdmin(HttpSession session) {
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        String role = (String) session.getAttribute("role");
        
        return isLoggedIn != null && isLoggedIn && "ADMIN".equalsIgnoreCase(role);
    }

    /**
     * Displays the admin dashboard with system overview.
     * Only accessible by users with ADMIN role.
     * 
     * @param session HTTP session to verify admin status
     * @param model Model to pass data to the view
     * @return View name or redirect URL
     */
    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        if (!isAdmin(session)) {
            System.out.println("Unauthorized access attempt to admin dashboard");
            return "redirect:/login";
        }

        String username = (String) session.getAttribute("username");
        System.out.println("Admin dashboard accessed by: " + username);
        
        model.addAttribute("username", username);
        
        // model.addAttribute("totalProducts", bookService.getTotalBooks());
        // model.addAttribute("totalOrders", orderService.getTotalOrders());
        // model.addAttribute("lowStockCount", bookService.getLowStockBooks(10).size());
        
        return "admin-dashboard";
    }

    /**
     * Displays the product management page.
     * Only accessible by admins.
     * 
     * @param session HTTP session to verify admin status
     * @param model Model to pass data to the view
     * @return View name or redirect URL
     */

     @Autowired
    private BookRepository bookRepository;

    @GetMapping("/admin/products")
    public String showAdminProducts(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "admin-products";
    }

    /**
     * Displays the order management page.
     * Only accessible by admins.
     * 
     * @param session HTTP session to verify admin status
     * @param model Model to pass data to the view
     * @return View name or redirect URL
     */

    @GetMapping("/orders")
    public String showOrders(HttpSession session, Model model) {
        if (!isAdmin(session)) {
            System.out.println("Unauthorized access attempt to admin orders");
            return "redirect:/login";
        }

        String username = (String) session.getAttribute("username");
        System.out.println("Admin orders page accessed by: " + username);
        
        model.addAttribute("username", username);
        
        return "admin-orders";
    }

    /**
     * Displays the user management page.
     * Only accessible by admins.
     * 
     * @param session HTTP session to verify admin status
     * @param model Model to pass data to the view
     * @return View name or redirect URL
     */
    @GetMapping("/users")
    public String showUsers(HttpSession session, Model model) {
        if (!isAdmin(session)) {
            System.out.println("Unauthorized access attempt to admin users");
            return "redirect:/login";
        }

        String username = (String) session.getAttribute("username");
        System.out.println("Admin users page accessed by: " + username);
        
        model.addAttribute("username", username);
        
        return "admin-users";
    }

    /**
     * Displays the inventory management page.
     * Only accessible by admins.
     * 
     * @param session HTTP session to verify admin status
     * @param model Model to pass data to the view
     * @return View name or redirect URL
     */
    @GetMapping("/inventory")
    public String showInventory(HttpSession session, Model model) {
        if (!isAdmin(session)) {
            System.out.println("Unauthorized access attempt to admin inventory");
            return "redirect:/login";
        }

        String username = (String) session.getAttribute("username");
        System.out.println("Admin inventory page accessed by: " + username);
        
        model.addAttribute("username", username);
        
        return "admin-inventory";
    }

    /**
     * Displays the reports page.
     * Only accessible by admins.
     * 
     * @param session HTTP session to verify admin status
     * @param model Model to pass data to the view
     * @return View name or redirect URL
     */
    @GetMapping("/reports")
    public String showReports(HttpSession session, Model model) {
        if (!isAdmin(session)) {
            System.out.println("Unauthorized access attempt to admin reports");
            return "redirect:/login";
        }

        String username = (String) session.getAttribute("username");
        System.out.println("Admin reports page accessed by: " + username);
        
        model.addAttribute("username", username);
        
        return "admin-reports";
    }

    /**
     * Displays the analytics page.
     * Only accessible by admins.
     * 
     * @param session HTTP session to verify admin status
     * @param model Model to pass data to the view
     * @return View name or redirect URL
     */
    @GetMapping("/analytics")
    public String showAnalytics(HttpSession session, Model model) {
        if (!isAdmin(session)) {
            System.out.println("Unauthorized access attempt to admin analytics");
            return "redirect:/login";
        }

        String username = (String) session.getAttribute("username");
        System.out.println("Admin analytics page accessed by: " + username);
        
        model.addAttribute("username", username);
        
        return "admin-analytics";
    }

    /**
     * Displays the system settings page.
     * Only accessible by admins.
     * 
     * @param session HTTP session to verify admin status
     * @param model Model to pass data to the view
     * @return View name or redirect URL
     */
    @GetMapping("/settings")
    public String showSettings(HttpSession session, Model model) {
        if (!isAdmin(session)) {
            System.out.println("Unauthorized access attempt to admin settings");
            return "redirect:/login";
        }

        String username = (String) session.getAttribute("username");
        System.out.println("Admin settings page accessed by: " + username);
        
        model.addAttribute("username", username);
        
        return "admin-settings";
    }
}