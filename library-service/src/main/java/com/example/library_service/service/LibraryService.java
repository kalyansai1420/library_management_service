package com.example.library_service.service;

import java.util.List;

import com.example.library_service.dto.LibraryDto;

public interface LibraryService {
    List<LibraryDto> getAllBooks();
    LibraryDto getBookById(Long bookId);
    LibraryDto createBook(LibraryDto libraryDto);
    LibraryDto updateBook(Long bookId, LibraryDto libraryDto);
    void deleteBook(Long bookId);

    List<LibraryDto> getAllUsers();
    LibraryDto getUserById(String username);
    LibraryDto createUser(LibraryDto libraryDto);
    LibraryDto updateUser(String username, LibraryDto libraryDto);
    void deleteUser(String username);

    void createBookByUser(String username, Long bookId);
    void deleteBookByUser(String username, Long bookId);
}
