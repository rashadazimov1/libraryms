package com.project.libraryms.dto.bookdto;

import com.project.libraryms.dto.authorDto.AuthorDto;
import com.project.libraryms.dto.categoryDto.CategoryDto;
import com.project.libraryms.dto.dto.RentBookDto;
import com.project.libraryms.dto.dto.StockDTO;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class BookDto {


    private String barCode;
    private String title;
    private String description;
    private Set<AuthorDto> authors = new HashSet<>();
    private Set<RentBookDto> bookRentSet;
    private Set<StockDTO> stockSet;
    private Set<CategoryDto> categorySet;


    public BookDto(String barCode, String title, String description) {
        this.barCode = barCode;
        this.title = title;
        this.description = description;

    }

    public BookDto() {

    }

    //private Set<Category> categorySet;

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

    public Set<AuthorDto> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorDto> authors) {
        this.authors = authors;
    }

    public Set<RentBookDto> getBookRentSet() {
        return bookRentSet;
    }

    public void setBookRentSet(Set<RentBookDto> bookRentSet) {
        this.bookRentSet = bookRentSet;
    }

    public Set<StockDTO> getStockSet() {
        return stockSet;
    }

    public void setStockSet(Set<StockDTO> stockSet) {
        this.stockSet = stockSet;
    }

    public Set<CategoryDto> getCategorySet() {
        return categorySet;
    }

    public void setCategorySet(Set<CategoryDto> categorySet) {
        this.categorySet = categorySet;
    }


}
