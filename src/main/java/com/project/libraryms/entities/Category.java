package com.project.libraryms.entities;

import javax.persistence.*;


@Entity
@Table(name = "categories_table")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Enumerated(EnumType.STRING)
	private Categories categories;


	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "book_id")
	private Book book;

	public Category() {
	}

	public Category(Categories categories) {
		this.categories = categories;
	}

	public Category(Long id, Categories categories, Book book) {
		this.id = id;
		this.categories = categories;
		this.book = book;
	}

	public Category(Categories categories, Book book) {
		this.categories = categories;
		this.book = book;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Categories getCategories() {
		return categories;
	}

	public void setCategories(Categories categories) {
		this.categories = categories;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
}


