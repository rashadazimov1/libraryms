package com.project.libraryms.repos;

import com.project.libraryms.entities.Categories;
import com.project.libraryms.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByCategories(Categories categories);

}
