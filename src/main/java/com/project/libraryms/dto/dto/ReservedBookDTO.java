package com.project.libraryms.dto.dto;

public class ReservedBookDTO {
    private Long id;
    private String barCode;
    private String title;
    private String fullName;

    public ReservedBookDTO() {
    }

    public ReservedBookDTO(Long id, String barCode, String title, String fullName) {
        this.id = id;
        this.barCode = barCode;
        this.title = title;
        this.fullName = fullName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
