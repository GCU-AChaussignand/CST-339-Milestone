package com.gcu.bookstore.controller;

import com.gcu.bookstore.model.Book;
import com.gcu.bookstore.model.CartItem;
import com.gcu.bookstore.model.User;
import com.gcu.bookstore.service.BookService;
import com.gcu.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartController {
    
    @Autowired
    private CartService cartService;
    
    @Autowired
    private BookService bookService;
    
    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        
        List<CartItem> cartItems = cartService.getCartItems(user);
        BigDecimal subtotal = cartService.calculateSubtotal(cartItems);
        BigDecimal tax = cartService.calculateTax(subtotal);
        BigDecimal shipping = cartItems.isEmpty() ? BigDecimal.ZERO : new BigDecimal("5.99");
        BigDecimal total = cartService.calculateTotal(subtotal, tax, shipping);
        
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("subtotal", subtotal);
        model.addAttribute("tax", tax);
        model.addAttribute("shipping", shipping);
        model.addAttribute("total", total);
        
        return "cart";
    }
    
    @PostMapping("/add/{bookId}")
    public String addToCart(@PathVariable Long bookId,
                           @RequestParam(defaultValue = "1") Integer quantity,
                           HttpSession session,
                           RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        
        Optional<Book> book = bookService.getBookById(bookId);
        if (book.isPresent()) {
            cartService.addToCart(user, book.get(), quantity);
            redirectAttributes.addFlashAttribute("message", "Book added to cart!");
        }
        
        return "redirect:/cart";
    }
    
    @PostMapping("/update/{itemId}")
    public String updateQuantity(@PathVariable Long itemId,
                                @RequestParam Integer quantity,
                                RedirectAttributes redirectAttributes) {
        cartService.updateQuantity(itemId, quantity);
        redirectAttributes.addFlashAttribute("message", "Quantity updated!");
        return "redirect:/cart";
    }
    
    @PostMapping("/remove/{itemId}")
    public String removeItem(@PathVariable Long itemId,
                            RedirectAttributes redirectAttributes) {
        cartService.removeItem(itemId);
        redirectAttributes.addFlashAttribute("message", "Item removed from cart!");
        return "redirect:/cart";
    }
    
    @PostMapping("/checkout")
    public String checkout(HttpSession session, RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        
        cartService.clearCart(user);
        redirectAttributes.addFlashAttribute("message", "Order placed successfully!");
        return "redirect:/";
    }
}