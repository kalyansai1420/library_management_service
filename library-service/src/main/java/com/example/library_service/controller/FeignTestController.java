package com.example.library_service.controller;

import com.example.library_service.feign.BookFeignClient;
import com.example.library_service.feign.UserFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignTestController {

    private final BookFeignClient bookFeignClient;
    private final UserFeignClient userFeignClient;

    public FeignTestController(BookFeignClient bookFeignClient, UserFeignClient userFeignClient) {
        this.bookFeignClient = bookFeignClient;
        this.userFeignClient = userFeignClient;
    }

    @GetMapping("/test-feign")
    public String testFeignClients() {
        String bookServiceResponse = bookFeignClient.getAllBooks().toString();
        String userServiceResponse = userFeignClient.getAllUsers().toString();
        return "Book Service Response: " + bookServiceResponse + "\nUser Service Response: " + userServiceResponse;
    }
}
