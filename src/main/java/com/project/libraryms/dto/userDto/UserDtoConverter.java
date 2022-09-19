package com.project.libraryms.dto.userDto;

import com.project.libraryms.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {
    public UserDto userDtoConverter(User user) {
        UserDto userDto = new UserDto();
        userDto.setFullName(user.getFullName());
        userDto.setUsername(user.getFullName());
        userDto.setEmail(user.getEmail());
        userDto.setAddress(user.getAddress());
        userDto.setPassword(user.getPassword());

        return userDto;
    }



}
