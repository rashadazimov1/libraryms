package com.project.libraryms.service.impl;


import com.project.libraryms.entities.Categories;
import com.project.libraryms.entities.Category;
import com.project.libraryms.repos.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl  {


	@Autowired
	 private CategoryRepository categoryRepository;


	public Category createLibrary(Category category){
		return categoryRepository.save(category);
	}
	public void deleteCategoryById(Long id){
		Optional<Category> foundCategory = categoryRepository.findById(id);
		foundCategory.ifPresent(category -> categoryRepository.deleteById(category.getId()));
	}
	public Optional<Category> getCategoryById(Long id){
		return categoryRepository.findById(id);
	}
	public List<Category> getAllCategories(){
		return (List<Category>) categoryRepository.findAll();
	}

	public List<Category> getItemByCategory(Categories categories){
		return categoryRepository.findAllByCategories(categories);
	}
























//
//	private final CategoryDtoConverter categoryDtoConverter;
//
//	public CategoryServiceImpl(CategoryDtoConverter categoryDtoConverter) {
//		this.categoryDtoConverter = categoryDtoConverter;
//	}
//
//	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
//	@Override
//	public List<Category> findAllCategories() {
//		return categoryRepository.findAll();
//	}
//
//	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
//	@Override
//	public Category findCategoryById(Long id) throws NotFoundException {
//		return categoryRepository.findById(id)
//				.orElseThrow(() -> new NotFoundException(String.format("Category not found  with ID %d", id)));
//	}



//	@Override
//	public CategoryDto updateCategoryDto(Long id, UpdateRequestCategoryDto updateRequestCategoryDto) {
//		Optional<Category> optionalCategoryDto=categoryRepository.findById(id);
//		optionalCategoryDto.ifPresent( category->{
//			category.setId(updateRequestCategoryDto.getId());
//			category.setName(updateRequestCategoryDto.getName());
//			category.setBooks(category.getBooks());
//			categoryRepository.save(category);
//
//
//		});
//		return optionalCategoryDto.map(categoryDtoConverter::categoryConverter).orElse(new CategoryDto());
//
//	}

//	@Override
//	public CategoryDto createCategoryDto(CreateRequestCategoryDto createRequestCategoryDto) {
//		Category category=new Category();
//		category.setId(createRequestCategoryDto.getId());
//		category.setName(createRequestCategoryDto.getName());
//		category.setBooks(category.getBooks());
//		categoryRepository.save(category);
//		return categoryDtoConverter.categoryConverter(category);
//
//	}

//	@Override
//	public List<CategoryDto> getCategories() {
//		List<Category> categoryList= categoryRepository.findAll();
//		List<CategoryDto> categoryDtoList = new ArrayList<>();
//		for (Category category : categoryList) {
//			categoryDtoList.add(categoryDtoConverter.categoryConverter(category));
//		}
//		return categoryDtoList;
//	}


//
//
//	@Override
//	public void createCategory(Category category) {
//		categoryRepository.save(category);
//	}
//
//	@Override
//	public void updateCategory(Category category) {
//		categoryRepository.save(category);
//	}
//
//	@Override
//	public void deleteCategory(Long id) throws NotFoundException {
//		final Category category = categoryRepository.findById(id)
//				.orElseThrow(() -> new NotFoundException(String.format("Category not found  with ID %d", id)));
//
//		categoryRepository.deleteById(category.getId());
//	}
//
//	@Override
//	public CategoryDto updateCategoryDto(Long id, UpdateRequestCategoryDto updateRequestCategoryDto) {
//		return null;
//	}
//
//	@Override
//	public CategoryDto createCategoryDto(CreateRequestCategoryDto createRequestCategoryDto) {
//		return null;
//	}

}
