package com.project.libraryms.mapper;

import com.project.libraryms.dto.bookdto.BookDto;
import com.project.libraryms.entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)

public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDto bookEntityToDto(Book book);

    Book bookDtoToEntity(BookDto bookDto);

    List<BookDto> toDto1List(List<Book> books);
}
