package com.project.libraryms.dto.categoryDto;


import com.project.libraryms.entities.Book;

import java.util.HashSet;
import java.util.Set;

public class BaseRequestCategory {
    private String name;
    private Set<Book> books = new HashSet<Book>();

    public BaseRequestCategory() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
