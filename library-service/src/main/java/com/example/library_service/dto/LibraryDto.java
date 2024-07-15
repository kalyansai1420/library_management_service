package com.example.library_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LibraryDto {
    private Long id;
    private String username;
    private Long bookId;
}
