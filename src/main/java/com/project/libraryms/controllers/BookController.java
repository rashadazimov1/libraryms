package com.project.libraryms.controllers;

import com.project.libraryms.entities.Book;
import com.project.libraryms.exception.DeleteDetails;
import com.project.libraryms.exception.UnprocessableEntityException;
import com.project.libraryms.service.impl.BookServiceImpl;

import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {


    private final BookServiceImpl bookService;

    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/id/{id}")
    public Optional<Book> getOneItemById(@PathVariable Long id) throws NotFoundException {
        Optional<Book> item = bookService.getBookById(id);
        if (item.isPresent()){
            return item;
        }else {
            throw new NotFoundException("Item not found with id :" + id);
        }
    }


    @GetMapping("/all")
    public Iterable<Book> getAllBooks(){
        return bookService.getAllBooks();
    }


    @PostMapping("/new")
    public Book createNewBook(@RequestBody Book book) {
        if (book.getTitle() != null && book.getBarCode() != null){
            return bookService.createBook(book);
        }else {
            throw new UnprocessableEntityException("Title & BarCode are required!");
        }
    }


    @PutMapping("/id/{id}")
    public Book updateBook(@RequestBody Book book , Long id) throws NotFoundException {
        return bookService.updateItem(book, id);
    }


    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable Long id) throws NotFoundException {
        Optional<Book> item = bookService.getBookById(id);
        if (item.isPresent()){
            bookService.deleteBookById(id);
            return new ResponseEntity<>(new DeleteDetails("Delete request", "Book with id :"+id+" is successfully deleted!"), HttpStatus.OK);
        }else {
            throw new NotFoundException("Book not found with id :" + id);
        }
    }




















}
