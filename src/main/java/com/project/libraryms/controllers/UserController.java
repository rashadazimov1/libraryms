package com.project.libraryms.controllers;

import com.project.libraryms.entities.User;
import com.project.libraryms.exception.UserNotFoundException;
import com.project.libraryms.requests.UserRequest;
import com.project.libraryms.responses.UserResponse;
import com.project.libraryms.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}


	@PostMapping("/save")
	public ResponseEntity<Void> createUser(@RequestBody User newUser) {
		UserRequest user = userService.createUser(newUser);
		if (user != null)
			return new ResponseEntity<>(HttpStatus.CREATED);
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@GetMapping("/{userId}")
	public UserResponse getOneUser(@PathVariable Long userId) {
		User user = userService.getOneUserById(userId);
		if (user == null) {
			throw new UserNotFoundException();
		}
		return new UserResponse(user);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<Void> updateOneUser(@PathVariable Long userId, @RequestBody User newUser) {
		User user = userService.updateOneUser(userId, newUser);
		if (user != null)
			return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@DeleteMapping("/{userId}")
	public void deleteOneUser(@PathVariable Long userId) {
		userService.deleteById(userId);
	}


	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	private void handleUserNotFound() {

	}

}