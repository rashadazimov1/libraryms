package com.project.libraryms.service.impl;

import com.project.libraryms.entities.Author;
import com.project.libraryms.exception.NotFoundException;
import com.project.libraryms.repos.AuthorRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class AuthorServiceImpl {




	 private final AuthorRepository authorRepository;

	public AuthorServiceImpl(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}


	public Author createAuthor(Author author){
		return authorRepository.save(author);
	}
	public void deleteAuthorById(Long id){
		Optional<Author> foundAuthor = authorRepository.findById(id);
		authorRepository.deleteById(foundAuthor.get().getId());
	}
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Optional<Author> getAuthorById(Long id){
		return authorRepository.findById(id);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Iterable<Author> getAllAuthors(){
		return authorRepository.findAll();
	}
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Iterable<Author> findAuthorByItemsId(Long id){
		return authorRepository.findAuthorByItems_Id(id);
	}
	@CacheEvict(value = "book", allEntries = true)
	public Author updateAuthor(Author newAuthor, Long id) throws NotFoundException {
		return authorRepository.findById(id)
				.map(author -> {
					author.setFullName(newAuthor.getFullName());
					author.setBirthDate(newAuthor.getBirthDate());
					return authorRepository.save(author);
				})
				.orElseThrow(() -> new NotFoundException("Author not found with id :" + id)
				);
	}

























//	private final AuthorRepository authorRepository;
//
//	public AuthorServiceImpl(AuthorRepository authorRepository,  ) {
//		this.authorRepository = authorRepository;
//
//	}
//
//
//
//	@Override
//	public AuthorDto createAuthorDto(CreateRequestAuthorDto createRequestAuthorDto) {
//		Author author=new Author();
//		author.setId(createRequestAuthorDto.getId());
//		//author.setName(createRequestAuthorDto.getName());
//		//author.setDescription(createRequestAuthorDto.getDescription());
//		//author.setBooks(createRequestAuthorDto.getBooks());
//		authorRepository.save(author);
//		return authorDtoConverter.authorConverter(author);
//	}
//
//	@Override
//	public AuthorDto updateAuthorDto(Long id, UpdateRequestAuthorDto updateRequestAuthorDto) {
//		Optional<Author> optionalAuthorDto = authorRepository.findById(id);
//		optionalAuthorDto.ifPresent( author->{
//			author.setId(updateRequestAuthorDto.getId());
//			//author.setName(updateRequestAuthorDto.getName());
//			//author.setDescription(updateRequestAuthorDto.getDescription());
//			//author.setBooks(updateRequestAuthorDto.getBooks());
//			authorRepository.save(author);
//
//		});
//		return optionalAuthorDto.map(authorDtoConverter::authorConverter).orElse(new AuthorDto());
//	}
//
//
//
//	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
//	public List<AuthorDto> getAuthors() {
//		List<Author> authorList= authorRepository.findAll();
//		List<AuthorDto> authorDtoList = new ArrayList<>();
//		for (Author author : authorList) {
//			authorDtoList.add(authorDtoConverter.authorConverter(author));
//		}
//		return authorDtoList;
//	}

//	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
//	@Override
//	public Author findAuthorById(Long id) {
//		return authorRepository.findById(id)
//				.orElseThrow(() -> new NotFoundException(String.format("Author not found with ID %d", id)));
//	}



//	@Override
//	public void deleteAuthor(Long id) {
//		final Author author = authorRepository.findById(id)
//				.orElseThrow(() -> new NotFoundException(String.format("Author not found with ID %d", id)));
//
//		authorRepository.deleteById(author.getId());
//	}







	//Artix
//
//	@Override
//	public void updateAuthor(Author author) {
//		authorRepository.save(author);
//	}
//
//	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
//	@Override
//	public List<Author> findAllAuthors() {
//		return authorRepository.findAll();
//	}
//
//
//	@Override
//	public void createAuthor(Author author) {
//		authorRepository.save(author);
//	}


}
