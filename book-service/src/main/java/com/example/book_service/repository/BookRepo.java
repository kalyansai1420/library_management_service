package com.example.book_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.book_service.model.Book;

@Repository
public interface BookRepo extends JpaRepository<Book,Long> {
	
}
