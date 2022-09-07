package com.project.libraryms.dto.userDto;




public class UserRequest {
	
	String userName;
	String password;


	public UserRequest(String userName, String password) {
		this.userName = userName;
		this.password = password;

	}

	public UserRequest() {
	}




	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
