package com.project.libraryms.dto.authorDto;


import com.project.libraryms.dto.bookdto.BookDto;

import java.util.HashSet;
import java.util.Set;


public class AuthorDto {
    private String fullName;
    private String birthDate;
    private Set<BookDto> books = new HashSet<>();


    public AuthorDto() {
    }

    public AuthorDto(String fullName, String birthDate) {
        this.fullName = fullName;
        this.birthDate = birthDate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Set<BookDto> getBooks() {
        return books;
    }

    public void setBooks(Set<BookDto> books) {
        this.books = books;
    }
}
