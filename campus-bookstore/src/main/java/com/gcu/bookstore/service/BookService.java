package com.gcu.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gcu.bookstore.model.Book;
import com.gcu.bookstore.repository.BookRepository;

@Service
@Transactional
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;
    
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }
    
    public List<Book> searchBooks(String query) {
        return bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(query, query);
    }
    
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
    
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
    
    public List<Book> getAvailableBooks() {
        return bookRepository.findByStockGreaterThan(0);
    }
}