package com.example.user_service.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.user_service.dto.UserDto;
import com.example.user_service.mapper.UserMapper;
import com.example.user_service.model.User;
import com.example.user_service.repository.UserRepo;
import com.example.user_service.service.UserService;
import com.example.user_service.helper.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService{
    
    // constructor injection
    private UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo){
        this.userRepo = userRepo; 
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream()
                    .map(UserMapper::mapToUserDto)
                    .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = userRepo.findByUsername(username);
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        User savedUser = userRepo.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDto updateUser(String username, UserDto userDto) {
        User existingUser = userRepo.findByUsername(username);
        if(existingUser == null){
            throw new ResourceNotFoundException("User not found with username: " +username);
        }
        UserMapper.mapToExistingUser(existingUser, userDto);
        User updatedUser = userRepo.save(existingUser);
        return UserMapper.mapToUserDto(updatedUser);

    }

    @Override
    public UserDto deleteUser(String username) {
        User user = userRepo.findByUsername(username);
        if(user == null){
            throw new ResourceNotFoundException("User not found with username: " +username);
        }
        userRepo.delete(user);
        return UserMapper.mapToUserDto(user);
    }
    
}
