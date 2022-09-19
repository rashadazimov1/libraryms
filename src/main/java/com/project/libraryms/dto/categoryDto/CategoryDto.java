package com.project.libraryms.dto.categoryDto;


import com.project.libraryms.dto.bookdto.BookDto;
import com.project.libraryms.entities.Categories;

public class CategoryDto {
    private Long id;
    private Categories categories;
    private BookDto book;

    public CategoryDto() {
    }

    public CategoryDto(Categories categories, BookDto book) {
        this.categories = categories;
        this.book = book;
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

    public BookDto getBook() {
        return book;
    }

    public void setBook(BookDto book) {
        this.book = book;
    }
}
