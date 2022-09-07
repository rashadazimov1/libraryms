package com.project.libraryms.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.project.libraryms.entities.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



public class JwtUserDetails implements UserDetails {

	public Long id;
	private String username;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

    private JwtUserDetails(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;

        this.password = password;
        this.authorities = authorities;
    }

	public JwtUserDetails() {
	}

	public static JwtUserDetails create(User user) {
		List<GrantedAuthority> authoritiesList = new ArrayList<>();
		authoritiesList.add(new SimpleGrantedAuthority("user"));

		return new JwtUserDetails(
				user.getId(),
				user.getUsername(),
				user.getPassword(),
				authoritiesList);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}




}
