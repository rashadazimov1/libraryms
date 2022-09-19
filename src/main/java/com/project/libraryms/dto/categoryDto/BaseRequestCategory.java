package com.project.libraryms.dto.categoryDto;


import com.project.libraryms.dto.bookdto.BookDto;

import java.util.HashSet;
import java.util.Set;

public class BaseRequestCategory {
    private String name;
    private Set<BookDto> books = new HashSet<BookDto>();

    public BaseRequestCategory() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BookDto> getBooks() {
        return books;
    }

    public void setBooks(Set<BookDto> books) {
        this.books = books;
    }
}
