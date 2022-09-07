package com.project.libraryms.repos;
import com.project.libraryms.dto.dto.RentBookDto;
import com.project.libraryms.dto.dto.ReservedBookDTO;
import com.project.libraryms.entities.BookRent;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface BookRentRepository extends CrudRepository<BookRent, Long> {

//    @Query("SELECT new com.project.libraryms.dto.dto.RentBookDto( il.id, i.title, i.barCode, il.creationDate, il.dueDate) FROM BookRent il INNER JOIN il.item i INNER JOIN il.user u INNER JOIN u.fullName l WHERE l.email=:email AND il.isConfirmed = TRUE AND il.isReturned = FALSE")
//    List<RentBookDto> findByBorrowedBookByUserEmail(String email);

    @Query("SELECT new com.project.libraryms.dto.dto.RentBookDto( i.title, i.barCode, il.creationDate, il.dueDate) FROM BookRent il INNER JOIN il.book i INNER JOIN il.user u WHERE u.id=:id AND il.isConfirmed = TRUE AND il.isReturned = FALSE")
    List<RentBookDto> findByBorrowedBookByUserId(Long id);

    @Query("SELECT new  com.project.libraryms.dto.dto.RentBookDto( il.id, i.title, i.barCode, il.creationDate, il.dueDate) FROM BookRent il INNER JOIN il.book i INNER JOIN il.user u WHERE u.id=:id AND il.isConfirmed = FALSE")
    List<RentBookDto> findByReservedBookByUserId(Long id);

    @Query("SELECT DISTINCT il FROM BookRent il INNER JOIN FETCH il.book i WHERE i.barCode =:barCode")
    BookRent findByBook_BarCode(@Param("barCode")String barCode);

    @Query("SELECT new com.project.libraryms.dto.dto.ReservedBookDTO( il.id, i.barCode, i.title, u.fullName) FROM BookRent il INNER JOIN il.book i INNER JOIN il.user u WHERE il.isConfirmed = FALSE")
    List<ReservedBookDTO> findAllReservedBooksByUserId();

    BookRent findBookLendingById(Long id);

}
