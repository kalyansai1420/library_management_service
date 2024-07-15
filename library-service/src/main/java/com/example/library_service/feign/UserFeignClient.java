package com.example.library_service.feign;

import com.example.library_service.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user-service", url = "http://localhost:8081")
public interface UserFeignClient {

    @GetMapping("/users")
    List<UserDto> getAllUsers();

    @GetMapping("/users/{username}")
    UserDto getUserByUsername(@PathVariable("username") String username);

    @PostMapping("/users")
    void createUser(@RequestBody UserDto userDto);

    @PutMapping("/users/{username}")
    void updateUser(@PathVariable("username") String username, @RequestBody UserDto userDto);

    @DeleteMapping("/users/{username}")
    void deleteUser(@PathVariable("username") String username);
}
