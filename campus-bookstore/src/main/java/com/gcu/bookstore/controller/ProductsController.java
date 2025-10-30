package com.gcu.bookstore.controller;

import com.gcu.bookstore.model.Book;
import com.gcu.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductsController {
    
    @Autowired
    private BookService bookService;
    
    @GetMapping
    public String products(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "products";
    }
    
    @GetMapping("/{id}")
    public String bookDetails(@PathVariable Long id, Model model) {
        Optional<Book> book = bookService.getBookById(id);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            return "bookDetails";
        }
        return "redirect:/products";
    }
    
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "addProduct";
    }
    
    @PostMapping("/add")
    public String addProduct(@Valid @ModelAttribute Book book, 
                            BindingResult result,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "addProduct";
        }
        bookService.saveBook(book);
        redirectAttributes.addFlashAttribute("message", "Book added successfully!");
        return "redirect:/products";
    }
    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Book> book = bookService.getBookById(id);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            return "editProduct";
        }
        return "redirect:/products";
    }
    
    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id,
                             @Valid @ModelAttribute Book book,
                             BindingResult result,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "editProduct";
        }
        book.setId(id);
        bookService.saveBook(book);
        redirectAttributes.addFlashAttribute("message", "Book updated successfully!");
        return "redirect:/products";
    }
    
    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        bookService.deleteBook(id);
        redirectAttributes.addFlashAttribute("message", "Book deleted successfully!");
        return "redirect:/products";
    }
}