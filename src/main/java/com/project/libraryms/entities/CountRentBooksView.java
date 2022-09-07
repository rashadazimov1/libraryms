package com.project.libraryms.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "view_table")
public class CountRentBooksView implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "book_id")
    private Long id;
    @Column(name = "bar_code")
    private String isbn;
    private String title;
    @Column(name = "quantity")
    private int borrowedItemQuantity;

    public CountRentBooksView() {
    }

    public CountRentBooksView(String isbn, String title, int borrowedItemQuantity) {
        this.isbn = isbn;
        this.title = title;
        this.borrowedItemQuantity = borrowedItemQuantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBorrowedItemQuantity() {
        return borrowedItemQuantity;
    }

    public void setBorrowedItemQuantity(int borrowedItemQuantity) {
        this.borrowedItemQuantity = borrowedItemQuantity;
    }
}
