package com.project.libraryms.serviceimpl;

import com.project.libraryms.dto.dto.RentBookDto;
import com.project.libraryms.dto.dto.ReservedBookDTO;
import com.project.libraryms.entities.BookRent;
import com.project.libraryms.exception.NotFoundException;
import com.project.libraryms.mapper.BookRentMapper;
import com.project.libraryms.repos.BookRentRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookRentService {


    private final BookRentRepository bookRentRepository;
    private final BookRentMapper bookRentMapper;

    public BookRentService(BookRentRepository bookRentRepository, BookRentMapper bookRentMapper) {
        this.bookRentRepository = bookRentRepository;
        this.bookRentMapper = bookRentMapper;
    }

//
//        public List<RentBookDto> findBorrowedBookAndCreationDueDateByUserEmail(String email){
//        return bookRentRepository.findByBorrowedBookByUserEmail(email);


    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public RentBookDto findBorrowedBookAndCreationDueDateByUserId(Long id) {
        BookRent bookRent = (BookRent) bookRentRepository.findByBorrowedBookByUserId(id);
        return bookRentMapper.rentBookEntityToDto(bookRent);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public RentBookDto findReservedBooksAndCreationDueDateByUserId(Long id) {
        BookRent bookRent = (BookRent) bookRentRepository.findByReservedBookByUserId(id);
        return bookRentMapper.rentBookEntityToDto(bookRent);

    }

//    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
//    public RentBookDto  findReservedBookByBarCode(String reservedBookBarCode) {
//        BookRent  bookRent=bookRentRepository.findByBook_BarCode(reservedBookBarCode);
//        return bookRentMapper.rentBookEntityToDto(bookRent);
//    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public void deleteReservedBook(Long id) {
        Optional<BookRent> bookRentOptional = bookRentRepository.findById(id);
        if (bookRentOptional.isPresent()) {
            bookRentRepository.deleteById(id);
        }
    }


    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Cacheable(value = "book")
    public List<ReservedBookDTO> getAllReservedBook() {
        List<BookRent> bookRentList = bookRentRepository.findAll();
        return bookRentMapper.toDtoList(bookRentList);

    }


    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public RentBookDto getReservedBookById(Long id) {
        BookRent bookRent = bookRentRepository.findBookLendingById(id);
        return bookRentMapper.rentBookEntityToDto(bookRent);
    }


    @CacheEvict(value = "book", allEntries = true)
    public RentBookDto updateBorrowedBook(RentBookDto bookRentupdate, Long id) throws NotFoundException {
        Optional<BookRent> bookRentOptional = bookRentRepository.findById(id);
        bookRentOptional.ifPresent(bookRent -> {
            bookRent.setCreationDate(bookRentupdate.getCreationDate());
            bookRent.setDueDate(bookRentupdate.getDueDate());
            bookRent.setReturned(bookRentupdate.isReturned());
            bookRent.setConfirmed(bookRentupdate.isConfirmed());
            bookRentRepository.save(bookRent);
        });
        return bookRentOptional.map(bookRentMapper::rentBookEntityToDto).orElse(new RentBookDto());
    }


    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public BookRent findBookLendingById(Long id) {
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
