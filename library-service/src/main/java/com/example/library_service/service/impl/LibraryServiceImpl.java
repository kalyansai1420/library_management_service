package com.example.library_service.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.library_service.dto.LibraryDto;
import com.example.library_service.dto.UserDto;
import com.example.library_service.feign.BookFeignClient;
import com.example.library_service.feign.UserFeignClient;
import com.example.library_service.helper.ResourceNotFoundException;
import com.example.library_service.mapper.LibraryMapper;
import com.example.library_service.model.Library;
import com.example.library_service.repository.LibraryRepo;
import com.example.library_service.service.LibraryService;

@Service
public class LibraryServiceImpl implements LibraryService {

    private final LibraryRepo libraryRepository;
    private final BookFeignClient bookFeignClient;
    private final UserFeignClient userFeignClient;

    public LibraryServiceImpl(LibraryRepo libraryRepository, BookFeignClient bookFeignClient,
            UserFeignClient userFeignClient) {
        this.libraryRepository = libraryRepository;
        this.bookFeignClient = bookFeignClient;
        this.userFeignClient = userFeignClient;
    }

    @Override
    public List<LibraryDto> getAllBooks() {
        return libraryRepository.findAll()
                .stream()
                .map(LibraryMapper::mapToLibraryDto)
                .collect(Collectors.toList());
    }

    @Override
    public LibraryDto getBookById(Long bookId) {
        Library library = libraryRepository.findByBookId(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));
        return LibraryMapper.mapToLibraryDto(library);
    }

    @Override
    public LibraryDto createBook(LibraryDto libraryDto) {
        Library library = LibraryMapper.mapToLibrary(libraryDto);
        library = libraryRepository.save(library);
        return LibraryMapper.mapToLibraryDto(library);
    }

    @Override
    public LibraryDto updateBook(Long bookId, LibraryDto libraryDto) {
        Library library = libraryRepository.findByBookId(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));
        LibraryMapper.mapToExistingLibrary(library, libraryDto);
        library = libraryRepository.save(library);
        return LibraryMapper.mapToLibraryDto(library);
    }

    @Override
    public void deleteBook(Long bookId) {
        Library library = libraryRepository.findByBookId(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));
        libraryRepository.delete(library);
    }

    @Override
    public List<LibraryDto> getAllUsers() {
        return userFeignClient.getAllUsers()
                .stream()
                .map(user -> new LibraryDto(null, user.getUsername(), null))
                .collect(Collectors.toList());
    }

    @Override
    public LibraryDto getUserById(String username) {
        UserDto userDto = userFeignClient.getUserByUsername(username);
        if (userDto == null) {
            throw new ResourceNotFoundException("User not found with username: " + username);
        }
        return new LibraryDto(null, userDto.getUsername(), null);
    }

    @Override
    public LibraryDto createUser(LibraryDto libraryDto) {
        userFeignClient.createUser(new UserDto(null, libraryDto.getUsername(), null));
        return libraryDto;
    }

    @Override
    public LibraryDto updateUser(String username, LibraryDto libraryDto) {
        userFeignClient.updateUser(username, new UserDto(null, libraryDto.getUsername(), null));
        return libraryDto;
    }

    @Override
    public void deleteUser(String username) {
        libraryRepository.deleteByUsername(username);
        userFeignClient.deleteUser(username);
    }

    @Override
    public void createBookByUser(String username, Long bookId) {
        Library library = new Library(null, username, bookId);
        libraryRepository.save(library);
    }

    @Override
    public void deleteBookByUser(String username, Long bookId) {
        Library library = libraryRepository.findByUsernameAndBookId(username, bookId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Association not found for username: " + username + " and bookId: " + bookId));
        libraryRepository.delete(library);
    }
}
