package com.project.libraryms.dto.authorDto;

import com.project.libraryms.entities.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorDtoConverter {
    public AuthorDto authorDtoConverter(Author author) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setFullName(author.getFullName());
        authorDto.setBirthDate(author.getBirthDate());
        author.setBooks(author.getBooks());
        return authorDto;
    }
}
