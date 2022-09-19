package com.project.libraryms.controllers;


import com.project.libraryms.entities.CountRentBooksView;
import com.project.libraryms.serviceimpl.CountRentBooksViewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/view")
public class CountBookRentViewController {


     private final CountRentBooksViewService countRentBooksViewService;

    public CountBookRentViewController(CountRentBooksViewService countRentBooksViewService) {
        this.countRentBooksViewService = countRentBooksViewService;
    }


    @GetMapping("/all")
    public List<CountRentBooksView> countBorrowedBooksViewList(){
        return countRentBooksViewService.countRentBooksViewsList();
    }


}
