package com.project.libraryms.serviceimpl;

import com.project.libraryms.entities.CountRentBooksView;
import com.project.libraryms.repos.CountRentBooksViewRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountRentBooksViewService {

    private final CountRentBooksViewRepository repository;

    public CountRentBooksViewService(CountRentBooksViewRepository repository) {
        this.repository = repository;
    }


    @CacheEvict(value = "books", allEntries = true)
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<CountRentBooksView> countRentBooksViewsList() {
        return (List<CountRentBooksView>) repository.findAll();
    }
}
