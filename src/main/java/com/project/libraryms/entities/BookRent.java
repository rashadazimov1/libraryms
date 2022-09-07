package com.project.libraryms.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name="bookrent_table")
public class BookRent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    @JsonFormat(pattern = "yyyy-mm-dd", shape = JsonFormat.Shape.STRING)
    private String creationDate;
    @NotEmpty
    @JsonFormat(pattern = "yyyy-mm-dd", shape = JsonFormat.Shape.STRING)
    private String dueDate;
    private boolean isConfirmed;
    private boolean isReturned;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;



    public BookRent() {
    }

    public BookRent(@NotEmpty String creationDate, @NotEmpty String dueDate, boolean isConfirmed, boolean isReturned) {
        this.creationDate = creationDate;
        this.dueDate = dueDate;
        this.isConfirmed = isConfirmed;
        this.isReturned = isReturned;
    }

    public BookRent(@NotEmpty String creationDate, @NotEmpty String dueDate, boolean isConfirmed, boolean isReturned, User user, Book book) {
        this.creationDate = creationDate;
        this.dueDate = dueDate;
        this.isConfirmed = isConfirmed;
        this.isReturned = isReturned;
        this.user = user;
        this.book = book;
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
    public boolean isReturned() {
        return isReturned;
    }
    public void setReturned(boolean returned) {
        isReturned = returned;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
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

    public boolean isConfirmed() {
        return isConfirmed;
    }
    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }
}
