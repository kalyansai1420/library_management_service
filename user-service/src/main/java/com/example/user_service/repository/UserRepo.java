package com.example.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.user_service.model.User;

public interface UserRepo extends JpaRepository<User, Long>{

    public User findByUsername(String username);
    
}
