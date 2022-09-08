package com.project.libraryms.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "authors_table")

public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotEmpty
	private String fullName;
	@NotEmpty
	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	private String birthDate;

	@JsonIgnore
	//@JsonManagedReference
	//@JsonBackReference
	@ManyToMany(mappedBy = "authors", cascade = { CascadeType.ALL })
	public Set<Book> books = new HashSet<>();

	public Author() {
	}

	public Author(String fullName, String birthDate) {
		this.fullName = fullName;
		this.birthDate = birthDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;

	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Author)) return false;
		Author author = (Author) o;
		return Objects.equals(getId(), author.getId()) && Objects.equals(getFullName(), author.getFullName()) && Objects.equals(getBirthDate(), author.getBirthDate()) && Objects.equals(getBooks(), author.getBooks());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getFullName(), getBirthDate(), getBooks());
	}
}
