package com.example.library_service.feign;

import com.example.library_service.dto.BookDto;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "book-service", url = "http://localhost:8080")
public interface BookFeignClient {
    @GetMapping("/books")
    List<BookDto> getAllBooks();

    @GetMapping("/books/{bookId}")
    BookDto getBookById(@PathVariable("bookId") Long bookId);
    
    @PostMapping("/books")
    void createBook(@RequestBody BookDto bookDto);

    @PutMapping("/books/{bookId}")
    void updateBook(@PathVariable("bookId") Long bookId, @RequestBody BookDto bookDto);

    @DeleteMapping("/books/{bookId}")
    void deleteBook(@PathVariable("bookId") Long bookId);
}