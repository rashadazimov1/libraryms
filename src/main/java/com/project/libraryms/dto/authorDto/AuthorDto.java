package com.project.libraryms.dto.authorDto;


import com.project.libraryms.entities.Book;

import java.util.HashSet;
import java.util.Set;


public class AuthorDto {
    private Long id;
    private String fullName;
    private String birthDate;
    public Set<Book> books = new HashSet<>();



    public AuthorDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
