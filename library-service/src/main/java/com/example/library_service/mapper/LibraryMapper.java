package com.example.library_service.mapper;


import com.example.library_service.dto.LibraryDto;
import com.example.library_service.model.Library;

public class LibraryMapper {

    private LibraryMapper(){

    }

    public static Library mapToLibrary(LibraryDto libraryDto){
        return new Library(
            libraryDto.getId(),
            libraryDto.getUsername(),
            libraryDto.getBookId()
        );
    }

    public static LibraryDto mapToLibraryDto(Library library){
        return new LibraryDto(
            library.getId(),
            library.getUsername(),
            library.getBookId()
        );
    }

    public static void mapToExistingLibrary(Library existingLibrary, LibraryDto libraryDto){
        existingLibrary.setUsername(libraryDto.getUsername());
        existingLibrary.setBookId(libraryDto.getBookId());
        
    }

    
}
