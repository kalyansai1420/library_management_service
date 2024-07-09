package com.example.user_service.mapper;

import com.example.user_service.dto.UserDto;
import com.example.user_service.model.User;

public class UserMapper {
    
    public static User mapToUser(UserDto userDto){
        return new User(
            userDto.getUserId(),
            userDto.getUsername(),
            userDto.getEmail()
        );
    }

    public static UserDto mapToUserDto(User user){
        return new UserDto(
            user.getUserId(),
            user.getUsername(),
            user.getEmail()
        );
    }

    public static void mapToExistingUser(User existingUser, UserDto userDto){
        existingUser.setUsername(userDto.getUsername());
        existingUser.setEmail(userDto.getEmail());
    }
    
}
