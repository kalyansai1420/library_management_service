package com.example.library_service.repository;

import com.example.library_service.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibraryRepo extends JpaRepository<Library, Long> {
    Optional<Library> findByBookId(Long bookId);
    Optional<Library> findByUsernameAndBookId(String username, Long bookId);
    void deleteByUsername(String username);
}
