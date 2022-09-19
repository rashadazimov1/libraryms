package com.project.libraryms.mapper;

import com.project.libraryms.dto.authorDto.AuthorDto;
import com.project.libraryms.entities.Author;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDto authorEntityToDto(Author author);

    Author authorDtoToEntity(AuthorDto authorDto);

    List<AuthorDto> toDtoList(List<Author> authors);

}
