package com.gcu.bookstore.util;

import com.gcu.bookstore.model.Book;
import com.gcu.bookstore.model.BookModel;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class for converting between Book entity and BookModel DTO.
 * Handles conversion of Book (JPA entity with BigDecimal) to BookModel (DTO with Double).
 * 
 * @author Campus Bookstore Team
 * @version 1.0
 */
public class BookConverter {

    /**
     * Converts a Book entity to a BookModel DTO.
     * 
     * @param book The Book entity to convert
     * @return BookModel DTO
     */
    public static BookModel toBookModel(Book book) {
        if (book == null) {
            return null;
        }
        
        BookModel model = new BookModel();
        model.setId(book.getId());
        model.setTitle(book.getTitle());
        model.setAuthor(book.getAuthor());
        model.setIsbn(book.getIsbn());
        model.setCourse(book.getCourse());
        model.setSubject(book.getSubject());
        
        // Convert BigDecimal to Double
        model.setPrice(book.getPrice() != null ? book.getPrice().doubleValue() : 0.0);
        model.setStock(book.getStock());
        model.setImageUrl(book.getImageUrl());
        model.setDescription(book.getDescription());
        
        return model;
    }

    /**
     * Converts a list of Book entities to a list of BookModel DTOs.
     * 
     * @param books List of Book entities
     * @return List of BookModel DTOs
     */
    public static List<BookModel> toBookModelList(List<Book> books) {
        if (books == null) {
            return null;
        }
        
        return books.stream()
                .map(BookConverter::toBookModel)
                .collect(Collectors.toList());
    }

    /**
     * Converts a BookModel DTO to a Book entity.
     * 
     * @param model The BookModel DTO to convert
     * @return Book entity
     */
    public static Book toBook(BookModel model) {
        if (model == null) {
            return null;
        }
        
        Book book = new Book();
        book.setId(model.getId());
        book.setTitle(model.getTitle());
        book.setAuthor(model.getAuthor());
        book.setIsbn(model.getIsbn());
        book.setCourse(model.getCourse());
        book.setSubject(model.getSubject());
        
        // Convert Double to BigDecimal
        book.setPrice(model.getPrice() != null ? 
            java.math.BigDecimal.valueOf(model.getPrice()) : 
            java.math.BigDecimal.ZERO);
        book.setStock(model.getStock());
        book.setImageUrl(model.getImageUrl());
        book.setDescription(model.getDescription());
        
        return book;
    }
}