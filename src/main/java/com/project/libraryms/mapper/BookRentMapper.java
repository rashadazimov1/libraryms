package com.project.libraryms.mapper;

import com.project.libraryms.dto.dto.RentBookDto;
import com.project.libraryms.dto.dto.ReservedBookDTO;
import com.project.libraryms.entities.BookRent;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface BookRentMapper {

    BookRentMapper INSTANCE = Mappers.getMapper(BookRentMapper.class);

    RentBookDto rentBookEntityToDto(BookRent bookRent);

    // RentBookDto rentDtoToEntity(BookRent rentBookDto);

    List<ReservedBookDTO> toDtoList(List<BookRent> bookRents);

}
