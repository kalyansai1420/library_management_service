package com.example.library_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.library_service.dto.LibraryDto;
import com.example.library_service.service.LibraryService;

@RestController
@RequestMapping("/library")
public class LibraryController {

    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/books")
    public ResponseEntity<List<LibraryDto>> getAllBooks() {
        return ResponseEntity.ok(libraryService.getAllBooks());
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<LibraryDto> getBookById(@PathVariable Long bookId) {
        return ResponseEntity.ok(libraryService.getBookById(bookId));
    }

    @PostMapping("/books")
    public ResponseEntity<LibraryDto> createBook(@RequestBody LibraryDto libraryDto) {
        return ResponseEntity.ok(libraryService.createBook(libraryDto));
    }

    @PutMapping("/books/{bookId}")
    public ResponseEntity<LibraryDto> updateBook(@PathVariable Long bookId, @RequestBody LibraryDto libraryDto) {
        return ResponseEntity.ok(libraryService.updateBook(bookId, libraryDto));
    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) {
        libraryService.deleteBook(bookId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users")
    public ResponseEntity<List<LibraryDto>> getAllUsers() {
        return ResponseEntity.ok(libraryService.getAllUsers());
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<LibraryDto> getUserById(@PathVariable String username) {
        return ResponseEntity.ok(libraryService.getUserById(username));
    }

    @PostMapping("/users")
    public ResponseEntity<LibraryDto> createUser(@RequestBody LibraryDto libraryDto) {
        return ResponseEntity.ok(libraryService.createUser(libraryDto));
    }

    @PutMapping("/users/{username}")
    public ResponseEntity<LibraryDto> updateUser(@PathVariable String username, @RequestBody LibraryDto libraryDto) {
        return ResponseEntity.ok(libraryService.updateUser(username, libraryDto));
    }

    @DeleteMapping("/users/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        libraryService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/users/{username}/books/{bookId}")
    public ResponseEntity<Void> createBookByUser(@PathVariable String username, @PathVariable Long bookId) {
        libraryService.createBookByUser(username, bookId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/{username}/books/{bookId}")
    public ResponseEntity<Void> deleteBookByUser(@PathVariable String username, @PathVariable Long bookId) {
        libraryService.deleteBookByUser(username, bookId);
        return ResponseEntity.noContent().build();
    }
}
