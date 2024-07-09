package com.example.user_service.service;

import java.util.List;

import com.example.user_service.dto.UserDto;

public interface UserService {
    
    List<UserDto> getAllUsers();
    UserDto getUserByUsername(String username);
    UserDto createUser(UserDto user);
    UserDto updateUser(String username,UserDto userDto);
    UserDto deleteUser(String username);

}
