package com.project.libraryms.service.impl;

import com.project.libraryms.entities.CountRentBooksView;
import com.project.libraryms.repos.CountRentBooksViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountRentBooksViewService {
    @Autowired
    private CountRentBooksViewRepository repository;


    @CacheEvict(value = "books", allEntries = true)
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<CountRentBooksView> countRentBooksViewsList(){
        return (List<CountRentBooksView>) repository.findAll();
    }
}
