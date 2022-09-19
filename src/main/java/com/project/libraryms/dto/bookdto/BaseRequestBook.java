package com.project.libraryms.dto.bookdto;

import com.project.libraryms.entities.Author;
import com.project.libraryms.entities.BookRent;
import com.project.libraryms.entities.Category;
import com.project.libraryms.entities.Stock;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class BaseRequestBook implements Serializable {

    private String barCode;
    private String title;
    private String description;
    private Set<Author> authors = new HashSet<>();
    private Set<BookRent> bookRentSet;
    private Set<Stock> stockSet;
    private Set<Category> categorySet;


    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Set<BookRent> getBookRentSet() {
        return bookRentSet;
    }

    public void setBookRentSet(Set<BookRent> bookRentSet) {
        this.bookRentSet = bookRentSet;
    }

    public Set<Stock> getStockSet() {
        return stockSet;
    }

    public void setStockSet(Set<Stock> stockSet) {
        this.stockSet = stockSet;
    }

    public Set<Category> getCategorySet() {
        return categorySet;
    }

    public void setCategorySet(Set<Category> categorySet) {
        this.categorySet = categorySet;
    }
}
