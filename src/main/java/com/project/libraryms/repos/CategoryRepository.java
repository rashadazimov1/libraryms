package com.project.libraryms.repos;

import com.project.libraryms.entities.Categories;
import com.project.libraryms.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface CategoryRepository extends CrudRepository<Category, Long> {
    List<Category> findAllByCategories(Categories categories);

}
