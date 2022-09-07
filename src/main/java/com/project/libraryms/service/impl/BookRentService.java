package com.project.libraryms.service.impl;

import com.project.libraryms.dto.dto.RentBookDto;
import com.project.libraryms.dto.dto.ReservedBookDTO;
import com.project.libraryms.entities.BookRent;
import com.project.libraryms.repos.BookRentRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookRentService {

    @Autowired
    BookRentService bookRentService;

    @Autowired
   private BookRentRepository bookRentRepository;

//    public List<RentBookDto> findBorrowedBookAndCreationDueDateByUserEmail(String email){
//        return bookRentRepository.findByBorrowedBookByUserEmail(email);
//    }
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<RentBookDto> findBorrowedBookAndCreationDueDateByUserId(Long id){
        return bookRentRepository.findByBorrowedBookByUserId(id);
    }
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<RentBookDto> findReservedBooksAndCreationDueDateByUserId(Long id){
        return bookRentRepository.findByReservedBookByUserId(id);
    }
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public BookRent findReservedBookByBarCode(String reservedBookBarCode) {
        return bookRentRepository.findByBook_BarCode(reservedBookBarCode);
    }
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public void deleteReservedBook(Long id){
        Optional<BookRent> itemLending = bookRentRepository.findById(id);
        if (itemLending.isPresent()){
            bookRentRepository.deleteById(id);
        }
    }
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Cacheable(value = "book")
    public List<ReservedBookDTO> getAllReservedBook(){
        return bookRentRepository.findAllReservedBooksByUserId();
    }
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public BookRent getReservedBookById(Long id){
        return bookRentRepository.findBookLendingById(id);
    }
    @CacheEvict(value = "book", allEntries = true)
    public BookRent updateBorrowedBook(BookRent bookRent1, Long id) throws NotFoundException {
        return bookRentRepository.findById(id)
                .map(bookRent -> {
                    bookRent.setCreationDate(bookRent1.getCreationDate());
                   bookRent.setDueDate(bookRent1.getDueDate());
                    bookRent.setReturned(bookRent1.isReturned());
                    bookRent.setConfirmed(bookRent1.isConfirmed());
                    return bookRentRepository.save(bookRent);
                })
                .orElseThrow(() -> new NotFoundException("The borrowed book not found with id :" + id)
                );
    }
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public BookRent findBookLendingById(Long id){
        return bookRentRepository.findBookLendingById(id);
    }
    public void returnBorrowedBook(Long id) throws NotFoundException {
      bookRentRepository.findById(id)
                .map(bookRent -> {
                    bookRent.setReturned(true);
                     return bookRentRepository.save(bookRent);
                })
                .orElseThrow(() -> new NotFoundException("The borrowed book not found with id :" + id));
    }

}
