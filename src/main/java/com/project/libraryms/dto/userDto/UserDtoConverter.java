package com.project.libraryms.dto.userDto;

import com.project.libraryms.entities.User;
import com.project.libraryms.requests.UserRequest;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {
    public UserRequest userDtoConverter(User user){
        UserRequest userDto=new UserRequest();
        userDto.setFullName(user.getFullName());
        userDto.setUsername(user.getFullName());
        userDto.setEmail(user.getEmail());
        userDto.setAddress(user.getAddress());
        userDto.setPassword(user.getPassword());

        return userDto;
    }



}
