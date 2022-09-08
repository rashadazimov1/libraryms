package com.project.libraryms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.redis.core.RedisHash;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity

@Table(name = "books_table")
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotEmpty
	private String barCode;
	@NotEmpty
	private String title;
	private String description;


	//@JsonManagedReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JsonIgnore
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "books_authors", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
	private Set<Author> authors = new HashSet<>();

	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<BookRent> bookRentSet;

	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Stock> stockSet;

	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Category> categorySet;

	public Book() {
	}

	public Book(Long id, String barCode, String title, String description, Set<Author> authors, Set<BookRent> bookRentSet, Set<Stock> stockSet, Set<Category> categorySet) {
		this.id = id;
		this.barCode = barCode;
		this.title = title;
		this.description = description;
		this.authors = authors;
		this.bookRentSet = bookRentSet;
		this.stockSet = stockSet;
		this.categorySet = categorySet;
	}

	public Book(String barCode, String title, String description) {
		this.barCode = barCode;
		this.title = title;
		this.description = description;
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

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Book)) return false;
		Book book = (Book) o;
		return Objects.equals(getId(), book.getId()) && Objects.equals(getBarCode(), book.getBarCode()) && Objects.equals(getTitle(), book.getTitle()) && Objects.equals(getDescription(), book.getDescription()) && Objects.equals(getAuthors(), book.getAuthors()) && Objects.equals(getBookRentSet(), book.getBookRentSet()) && Objects.equals(getStockSet(), book.getStockSet()) && Objects.equals(getCategorySet(), book.getCategorySet());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getBarCode(), getTitle(), getDescription(), getAuthors(), getBookRentSet(), getStockSet(), getCategorySet());
	}
}
