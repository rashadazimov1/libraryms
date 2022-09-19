package com.project.libraryms.serviceimpl;


import com.project.libraryms.dto.bookdto.BookDto;
import com.project.libraryms.dto.bookdto.BookDtoConverter;
import com.project.libraryms.dto.bookdto.CreateRequestBookDto;
import com.project.libraryms.dto.bookdto.UpdateRequestBookDto;
import com.project.libraryms.entities.Book;
import com.project.libraryms.exception.NotFoundException;
import com.project.libraryms.mapper.BookMapper;
import com.project.libraryms.repos.BookRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl {


    private final BookRepository bookRepository;
    private final BookDtoConverter bookDtoConverter;
    private final DirectExchange exchange;

    private final AmqpTemplate rabbitTemplate;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, BookDtoConverter bookDtoConverter, DirectExchange exchange, AmqpTemplate rabbitTemplate, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookDtoConverter = bookDtoConverter;
        this.exchange = exchange;
        this.rabbitTemplate = rabbitTemplate;

        this.bookMapper = bookMapper;
    }

    @RabbitListener(queues = "${sample.rabbitmq.queue}")
    public BookDto createBook(CreateRequestBookDto createRequestBookDto) {
        Book book = new Book();
        book.setTitle(createRequestBookDto.getTitle());
        book.setBarCode(createRequestBookDto.getBarCode());
        book.setDescription(createRequestBookDto.getDescription());
        book.setAuthors(book.getAuthors());
        book.setCategorySet(book.getCategorySet());
        bookRepository.save(book);
        rabbitTemplate.convertAndSend(exchange.getName(), "secondRoute");


        return bookDtoConverter.bookDtoConverter(book);
    }

    @CacheEvict(value = "book", allEntries = true)
    public void deleteBookById(Long id) {
        Optional<Book> foundItem = bookRepository.findById(id);
        bookRepository.deleteById(foundItem.get().getId());
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public BookDto getByBookDtoId(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        return bookOptional.map(bookMapper::bookEntityToDto).orElse(new BookDto());

    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Cacheable(value = "book")
    public List<BookDto> getAllBooks() {
        List<Book> bookList = (List<Book>) bookRepository.findAll();

        return bookMapper.toDto1List(bookList);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Optional<Book> findBookByTitle(String title) {
        return bookRepository.findBookByTitle(title);
    }

    @CacheEvict(value = "book", allEntries = true)
    public BookDto updateBook(UpdateRequestBookDto updateRequestBookDto, Long id) throws NotFoundException {
        Optional<Book> bookOptional = bookRepository.findById(id);
        bookOptional.ifPresent(book -> {
            book.setTitle(updateRequestBookDto.getTitle());
            book.setDescription(updateRequestBookDto.getDescription());
            book.setBarCode(updateRequestBookDto.getBarCode());
            book.setCategorySet(book.getCategorySet());
            book.setStockSet(book.getStockSet());
            book.setAuthors(book.getAuthors());
            bookRepository.save(book);
        });

        return bookOptional.map(bookDtoConverter::bookDtoConverter).orElseThrow(() -> new NotFoundException("Book not found with id :" + id));

    }


}
