package com.example.book_service.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.book_service.dto.BookDto;
import com.example.book_service.helper.ResourceNotFoundException;
import com.example.book_service.mapper.BookMapper;
import com.example.book_service.model.Book;
import com.example.book_service.repository.BookRepo;
import com.example.book_service.service.BookService;

@Service
public class BookServiceImpl implements BookService {

    private BookRepo bookRepo;

    public BookServiceImpl(BookRepo bookRepo){
        this.bookRepo = bookRepo;
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepo.findAll();
        return books.stream()
                    .map(BookMapper::mapToBookDto)
                    .collect(Collectors.toList()); 
    }

    @Override
    public BookDto getBookById(Long bookId) {
        Book book = bookRepo.findById(bookId)
                            .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));
         return BookMapper.mapToBookDto(book);
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        Book book = BookMapper.mapToBook(bookDto);
        Book savedBook = bookRepo.save(book);
        return BookMapper.mapToBookDto(savedBook);
    }

    @Override
    public BookDto updateBook(Long bookId, BookDto bookDto) {
        Book existingBook = bookRepo.findById(bookId)
                                .orElseThrow(() ->new ResourceNotFoundException("Book not found with ID: "+bookId));    
        BookMapper.mapToExistingBook(existingBook, bookDto);
        Book updatedBook = bookRepo.save(existingBook);
        return BookMapper.mapToBookDto(updatedBook);
    }

    @Override
    public BookDto deleteBook(Long bookId) {
        Book book = bookRepo.findById(bookId)
                            .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: "+bookId));
        bookRepo.delete(book); 
        return BookMapper.mapToBookDto(book);   
    }
    
}
