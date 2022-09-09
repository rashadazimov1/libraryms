package com.project.libraryms.service.impl;



import com.project.libraryms.entities.Book;
import com.project.libraryms.exception.NotFoundException;
import com.project.libraryms.repos.BookRepository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Optional;

@Service
@RequestScope
public class BookServiceImpl {



    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book){
        return bookRepository.save(book);
    }
    @CacheEvict(value = "book", allEntries = true)
    public void deleteBookById(Long id){
        Optional<Book> foundItem = bookRepository.findById(id);
        bookRepository.deleteById(foundItem.get().getId());
    }
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Optional<Book> getBookById(Long id){
        return bookRepository.findById(id);
    }
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Cacheable(value = "book")
    public Iterable<Book> getAllBooks(){
        return bookRepository.findAll();
    }
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Optional<Book> findBookByTitle(String title){
        return bookRepository.findBookByTitle(title);
    }
//    public Iterable<Book> findItemByAuthorName(String fullName){
//        return bookRepository.ffullName);
//    }
   @CacheEvict(value = "book", allEntries = true)
    public Book updateItem(Book newBook, Long id) throws NotFoundException {
        return bookRepository.findById(id)
                .map(item -> {
                    item.setBarCode(newBook.getBarCode());
                    item.setTitle(newBook.getTitle());
                    item.setDescription(newBook.getDescription());
                    return bookRepository.save(item);
                })
                .orElseThrow(() -> new NotFoundException("Item not found with id :" + id)
                );
    }























//	private final BookRepository bookRepository;
//	private final BookDtoConverter bookDtoConverter;
//
//
//	public BookServiceImpl(BookRepository bookRepository, BookDtoConverter bookDtoConverter) {
//		this.bookRepository = bookRepository;
//		this.bookDtoConverter = bookDtoConverter;
//
//	}

//	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
//	@Override
//	public List<Book> findAllBooks() {
//		return bookRepository.findAll();
//	}
//
//	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
//	@Override
//	public Book findBookById(Long id) {
//		return bookRepository.findById(id)
//				.orElseThrow(() -> new NotFoundException(String.format("Book not found with ID %d", id)));
//	}

//	@Override
//	public void createBook(Book book){
//		bookRepository.save(book);
//	}
//
//	@Override
//	public void updateBook(Book book){
//		bookRepository.save(book);
//	}







//

//



}
