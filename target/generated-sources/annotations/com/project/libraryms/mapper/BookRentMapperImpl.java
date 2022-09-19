package com.project.libraryms.mapper;

import com.project.libraryms.dto.dto.RentBookDto;
import com.project.libraryms.dto.dto.ReservedBookDTO;
import com.project.libraryms.dto.userDto.UserDto;
import com.project.libraryms.entities.BookRent;
import com.project.libraryms.entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-19T17:05:30+0400",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.13 (Oracle Corporation)"
)
public class BookRentMapperImpl implements BookRentMapper {

    @Override
    public RentBookDto rentBookEntityToDto(BookRent bookRent) {
        if ( bookRent == null ) {
            return null;
        }

        RentBookDto rentBookDto = new RentBookDto();

        rentBookDto.setUser( userToUserDto( bookRent.getUser() ) );
        rentBookDto.setConfirmed( bookRent.isConfirmed() );
        rentBookDto.setReturned( bookRent.isReturned() );
        rentBookDto.setId( bookRent.getId() );
        rentBookDto.setCreationDate( bookRent.getCreationDate() );
        rentBookDto.setDueDate( bookRent.getDueDate() );

        return rentBookDto;
    }

    @Override
    public List<ReservedBookDTO> toDtoList(List<BookRent> bookRents) {
        if ( bookRents == null ) {
            return null;
        }

        List<ReservedBookDTO> list = new ArrayList<ReservedBookDTO>( bookRents.size() );
        for ( BookRent bookRent : bookRents ) {
            list.add( bookRentToReservedBookDTO( bookRent ) );
        }

        return list;
    }

    protected UserDto userToUserDto(User user) {
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

    protected ReservedBookDTO bookRentToReservedBookDTO(BookRent bookRent) {
        if ( bookRent == null ) {
            return null;
        }

        ReservedBookDTO reservedBookDTO = new ReservedBookDTO();

        reservedBookDTO.setId( bookRent.getId() );

        return reservedBookDTO;
    }
}
