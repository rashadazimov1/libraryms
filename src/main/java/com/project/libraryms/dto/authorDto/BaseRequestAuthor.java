package com.project.libraryms.dto.authorDto;

import com.project.libraryms.dto.bookdto.BookDto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class BaseRequestAuthor implements Serializable {
    public Set<BookDto> books = new HashSet<>();
    private String fullName;
    private String birthDate;

    public BaseRequestAuthor() {
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
