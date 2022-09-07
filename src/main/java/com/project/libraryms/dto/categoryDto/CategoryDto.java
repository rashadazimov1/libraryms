package com.project.libraryms.dto.categoryDto;


import com.project.libraryms.entities.Book;
import com.project.libraryms.entities.Categories;

public class CategoryDto {
    private Long id;
    private Categories categories;
    private Book book;

    public CategoryDto() {
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
