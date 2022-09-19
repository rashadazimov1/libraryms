package com.project.libraryms.controllers;

import com.project.libraryms.dto.dto.RentBookDto;
import com.project.libraryms.dto.dto.ReservedBookDTO;
import com.project.libraryms.entities.BookRent;
import com.project.libraryms.exception.NotAcceptedException;
import com.project.libraryms.exception.NotFoundException;
import com.project.libraryms.serviceimpl.BookRentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookrent")
public class BookRentController {


        private final BookRentService bookRentService;

    public BookRentController(BookRentService bookRentService) {
        this.bookRentService = bookRentService;
    }



        @PostMapping("/id/{id}")
        public ResponseEntity<?> returnRentBooK(@PathVariable Long id) throws NotFoundException {
            BookRent bookRent = bookRentService.findBookLendingById(id);
            if (bookRent != null) {

                if (!bookRent.isReturned()) {

                    bookRentService.returnBorrowedBook(id);
                    return new ResponseEntity<>("The borrowed Item is returned!", HttpStatus.ACCEPTED);

                } else {
                    throw new NotAcceptedException("You have already returned this book!");
                }

            } else {
                throw new NotFoundException("The borrowed item not found with id :" + id);
            }
        }


    @GetMapping("/userid/{id}")
    public ResponseEntity<RentBookDto> getBarrowedBooksAndCreationDueDateByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(bookRentService.findBorrowedBookAndCreationDueDateByUserId(id));

    }

    @GetMapping("/userid/v1/{id}")
    public ResponseEntity<RentBookDto> findReversedBooksAndCreationDueDateByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(bookRentService.findReservedBooksAndCreationDueDateByUserId(id));

    }


//    @GetMapping("barcode/userid/{barcode}")
//    public ResponseEntity<RentBookDto>   getReservedBookByBarCode(@PathVariable String barcode){
//        return ResponseEntity.ok(bookRentService.findReservedBookByBarCode(barcode));
//
//    }


    @GetMapping("v1/all")
    public ResponseEntity<List<ReservedBookDTO>> getAllReservedBook() {
        return ResponseEntity.ok(bookRentService.getAllReservedBook());
    }

    @GetMapping("v1/rreversed/{id}")
    public ResponseEntity<RentBookDto> getReservedBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookRentService.getReservedBookById(id));
    }

    @PutMapping("v1/update//id/{id}")
    public ResponseEntity<RentBookDto> updateRentBook(@RequestBody RentBookDto rentBookDto, Long id) throws NotFoundException {
        return ResponseEntity.ok(bookRentService.updateBorrowedBook(rentBookDto, id));
    }

    @DeleteMapping("v1/delete/revresed/{id}")
    public ResponseEntity<Void> deleteReversedBooK(@PathVariable Long id) {
        bookRentService.deleteReservedBook(id);
        return ResponseEntity.ok().build();
    }


}
