package com.example.user_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.example.user_service.dto.UserDto;
import com.example.user_service.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/users")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    // List of all users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    } 
    // Get user by username
    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable("username") String username){
        UserDto user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    // Add a user
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
    
    // Delete a user
    @DeleteMapping("/{username}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable("username") String username){
        UserDto deletedUser = userService.deleteUser(username);
        return ResponseEntity.ok(deletedUser);
    }

    // Update a user
    @PutMapping("/{username}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("username") String username,@RequestBody UserDto userDto){
        UserDto updatedUser = userService.updateUser(username,userDto);
        return ResponseEntity.ok(updatedUser);
    }
}
