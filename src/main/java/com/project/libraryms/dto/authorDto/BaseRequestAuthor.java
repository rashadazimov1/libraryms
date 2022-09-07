package com.project.libraryms.dto.authorDto;

import com.project.libraryms.entities.Book;

import java.util.HashSet;
import java.util.Set;

public class BaseRequestAuthor {
    private String name;
    private String description;
    private Set<Book> books = new HashSet<Book>();

    public BaseRequestAuthor() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
