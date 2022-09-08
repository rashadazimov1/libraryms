package com.project.libraryms.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.lang.Nullable;

import java.io.Serializable;

@Entity
@Table(name="user_table")

public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;


	@Nullable
	private String fullName;

	@NotEmpty
	private String username;

	@Nullable
	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	private String birthDate;


	private String address;

	@Email
	private String email;
	@NotEmpty
	private String password;

	@JsonBackReference
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<BookRent> bookRentSet;







	public User(Long id, String username, String email, String password) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public User() {

	}

	public User(@Nullable String fullName, String username, @Nullable String birthDate, String address, String email, String password) {
		this.fullName = fullName;
		this.username = username;
		this.birthDate = birthDate;
		this.address = address;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Nullable
	public String getFullName() {
		return fullName;
	}

	public void setFullName(@Nullable String fullName) {
		this.fullName = fullName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Nullable
	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(@Nullable String birthDate) {
		this.birthDate = birthDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<BookRent> getBookRentSet() {
		return bookRentSet;
	}

	public void setBookRentSet(Set<BookRent> bookRentSet) {
		this.bookRentSet = bookRentSet;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;
		User user = (User) o;
		return Objects.equals(getId(), user.getId()) && Objects.equals(getFullName(), user.getFullName()) && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getBirthDate(), user.getBirthDate()) && Objects.equals(getAddress(), user.getAddress()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getBookRentSet(), user.getBookRentSet());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getFullName(), getUsername(), getBirthDate(), getAddress(), getEmail(), getPassword(), getBookRentSet());
	}
}
