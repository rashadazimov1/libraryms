package com.project.libraryms.repos;

import com.project.libraryms.entities.CountRentBooksView;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountRentBooksViewRepository extends CrudRepository<CountRentBooksView, Long> {
}
