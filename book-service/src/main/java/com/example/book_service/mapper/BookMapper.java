package com.example.book_service.mapper;

import com.example.book_service.dto.BookDto;
import com.example.book_service.model.Book;

public class BookMapper {

    private BookMapper(){
        
    }

    public static Book mapToBook(BookDto bookDto){
        return new Book(
            bookDto.getId(),
            bookDto.getName(),
            bookDto.getPublisher(),
            bookDto.getAuthor()
        );
    }

    public static BookDto mapToBookDto(Book book){
        return new BookDto(
            book.getId(), 
            book.getName(), 
            book.getPublisher(), 
            book.getAuthor()
        );
    }

    public static void mapToExistingBook(Book existingBook, BookDto bookDto){
        existingBook.setName(bookDto.getName());
        existingBook.setPublisher(bookDto.getPublisher());
        existingBook.setAuthor(bookDto.getAuthor());
    }
    
}
