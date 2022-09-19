package com.project.libraryms.controllers;

import com.project.libraryms.dto.userDto.CreateRequestUser;
import com.project.libraryms.dto.userDto.UpdateRequestUserDto;
import com.project.libraryms.dto.userDto.UserDto;
import com.project.libraryms.exception.UserNotFoundException;
import com.project.libraryms.serviceimpl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @PostMapping("/save")
    public ResponseEntity<UserDto> createUser(@RequestBody CreateRequestUser createRequestUser) {

        return ResponseEntity.ok(userService.createUser(createRequestUser));

    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());

    }


    @GetMapping("/get/userId/{userId}")
    public ResponseEntity<UserDto> getOneUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getByUserDtoId(userId));
    }

    @PutMapping("update//{userId}")
    public ResponseEntity<UserDto> updateOneUser(@PathVariable Long userId,
                                                 @RequestBody UpdateRequestUserDto updateRequestUserDto) {

        return ResponseEntity.ok(userService.updateOneUser(userId, updateRequestUserDto));

    }

    @DeleteMapping("delete//{userId}")
    public void deleteOneUser(@PathVariable Long userId) {
        userService.deleteById(userId);
    }


    @ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	private void handleUserNotFound() {

	}

}