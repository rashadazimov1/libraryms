package com.project.libraryms.mapper;

import com.project.libraryms.dto.userDto.UserDto;
import com.project.libraryms.entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-19T17:05:30+0400",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.13 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto userEntityToDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setFullName( user.getFullName() );
        userDto.setEmail( user.getEmail() );
        userDto.setPassword( user.getPassword() );
        userDto.setBirthDate( user.getBirthDate() );
        userDto.setUsername( user.getUsername() );
        userDto.setAddress( user.getAddress() );

        return userDto;
    }

    @Override
    public User userDtoToEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setFullName( userDto.getFullName() );
        user.setUsername( userDto.getUsername() );
        user.setBirthDate( userDto.getBirthDate() );
        user.setAddress( userDto.getAddress() );
        user.setEmail( userDto.getEmail() );
        user.setPassword( userDto.getPassword() );

        return user;
    }

    @Override
    public List<UserDto> toDtoList(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( users.size() );
        for ( User user : users ) {
            list.add( userEntityToDto( user ) );
        }

        return list;
    }
}
