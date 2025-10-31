package com.gcu.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcu.bookstore.model.BookModel;
import com.gcu.bookstore.service.BookService;

/**
 * Page Controller
 * Handles navigation for public pages with pagination support
 */
@Controller
public class PageController {

    @Autowired
    private BookService bookService;

    /**
     * Display home page
     */
    @GetMapping("/")
    public String home() {
        return "home";
    }

    /**
     * Display products page with optional search and pagination
     */
    @GetMapping("/products")
    public String products(
            @RequestParam(required = false) String search, 
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        
        // Validate page and size parameters
        if (page < 1) page = 1;
        if (size < 5) size = 5;
        if (size > 50) size = 50; // Max items per page
        
        List<BookModel> allBooks;
        
        if (search != null && !search.trim().isEmpty()) {
            allBooks = bookService.searchBooks(search);
            model.addAttribute("searchQuery", search);
            model.addAttribute("searchPerformed", true);
        } else {
            allBooks = bookService.getAllBooks();
            model.addAttribute("searchPerformed", false);
        }

        // Calculate pagination
        int totalBooks = allBooks.size();
        int totalPages = (int) Math.ceil((double) totalBooks / size);
        
        // Ensure page is within bounds
        if (page > totalPages && totalPages > 0) {
            page = totalPages;
        }
        
        // Calculate start and end indices
        int startIndex = (page - 1) * size;
        int endIndex = Math.min(startIndex + size, totalBooks);
        
        // Get books for current page
        List<BookModel> booksForPage = allBooks.subList(startIndex, endIndex);
        
        // Add attributes to model
        model.addAttribute("books", booksForPage);
        model.addAttribute("resultCount", totalBooks);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startIndex", startIndex + 1);
        model.addAttribute("endIndex", endIndex);
        
        return "products";
    }

    /**
     * Display individual book details
     *
     * @param id Book ID
     * @param model Spring Model
     * @return book details view
     */
    @GetMapping("/products/{id}")
    public String bookDetails(@PathVariable Long id, Model model) {
        BookModel book = bookService.getBookById(id);

        if (book == null) {
            return "redirect:/products";
        }

        model.addAttribute("book", book);
        return "book-details";
    }

    /**
     * Handle search from home page
     */
    @GetMapping("/search")
    public String search(@RequestParam String query) {
        // Redirect to products page with search parameter
        return "redirect:/products?search=" + query;
    }

    /**
     * Display cart page
     */
    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }

    /**
     * Display checkout page
     */
    @GetMapping("/checkout")
    public String checkout() {
        return "checkout";
    }
}