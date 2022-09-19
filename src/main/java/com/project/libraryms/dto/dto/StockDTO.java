package com.project.libraryms.dto.dto;

import com.project.libraryms.dto.bookdto.BookDto;

public class StockDTO {
    private Long id;
    private int quantity;
    private String barCode;
    private String title;
    private BookDto bookDto;


    public StockDTO() {
    }

    public StockDTO(Long id, int quantity, String barCode, String title) {
        this.id = id;
        this.quantity = quantity;
        this.barCode = barCode;
        this.title = title;
    }

    public StockDTO(int quantity, BookDto bookDto) {
        this.quantity = quantity;
        this.bookDto = bookDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

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

    public BookDto getBookDto() {
        return bookDto;
    }

    public void setBookDto(BookDto bookDto) {
        this.bookDto = bookDto;
    }
}
