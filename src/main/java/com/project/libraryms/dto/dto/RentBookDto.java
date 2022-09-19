package com.project.libraryms.dto.dto;

import com.project.libraryms.dto.bookdto.BookDto;
import com.project.libraryms.dto.userDto.UserDto;

public class RentBookDto {
    private Long id;
    private String title;
    private String barCode;
    private boolean isConfirmed;
    private boolean isReturned;
    private String creationDate;
    private String dueDate;
    private BookDto bookDto;
    private UserDto user;

    public RentBookDto(String title, boolean isConfirmed, boolean isReturned, String dueDate, BookDto bookDto, UserDto user) {
        this.title = title;
        this.isConfirmed = isConfirmed;
        this.isReturned = isReturned;
        this.dueDate = dueDate;
        this.bookDto = bookDto;
        this.user = user;
    }

    public RentBookDto(Long id, String title, String barCode, String creationDate, String dueDate) {
        this.id = id;
        this.title = title;
        this.barCode = barCode;
        this.creationDate = creationDate;
        this.dueDate = dueDate;
    }

    public RentBookDto(String title, String barCode, String creationDate, String dueDate) {

        this.title = title;
        this.barCode = barCode;
        this.creationDate = creationDate;
        this.dueDate = dueDate;
    }

    public RentBookDto() {
    }

    public BookDto getBookDto() {
        return bookDto;
    }

    public void setBookDto(BookDto bookDto) {
        this.bookDto = bookDto;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }
}
