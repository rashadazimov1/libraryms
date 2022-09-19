package com.project.libraryms.mapper;

import com.project.libraryms.dto.userDto.UserDto;
import com.project.libraryms.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userEntityToDto(User user);

    User userDtoToEntity(UserDto userDto);

    List<UserDto> toDtoList(List<User> users);

}
