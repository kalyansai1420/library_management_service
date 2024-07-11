package com.example.book_service.service;

import java.util.List;

import com.example.book_service.dto.BookDto;

public interface BookService {
    
    List<BookDto> getAllBooks();
    BookDto getBookById(Long id);
    BookDto createBook(BookDto bookDto);
    BookDto updateBook(Long id, BookDto bookDto);
    BookDto deleteBook(Long id);
    
}
