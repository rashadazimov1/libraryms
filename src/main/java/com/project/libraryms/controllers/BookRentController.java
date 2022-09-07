package com.project.libraryms.controllers;

import com.project.libraryms.entities.BookRent;
import com.project.libraryms.exception.NotAcceptedException;
import com.project.libraryms.service.impl.BookRentService;


import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                    throw new NotAcceptedException("You have already returned this item!");
                }

            } else {
                throw new NotFoundException("The borrowed item not found with id :" + id);
            }
        }

    }
