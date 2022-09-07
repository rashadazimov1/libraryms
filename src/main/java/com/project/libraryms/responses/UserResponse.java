package com.project.libraryms.responses;

import com.project.libraryms.entities.User;


public class UserResponse {
	
	Long id;
	String userName;

	public UserResponse(Long id, String userName) {
		this.id = id;
		this.userName = userName;
	}

	public UserResponse(User user) {
	}
}
