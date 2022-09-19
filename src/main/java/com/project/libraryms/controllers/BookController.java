package com.project.libraryms.controllers;

import com.project.libraryms.dto.bookdto.BookDto;
import com.project.libraryms.dto.bookdto.CreateRequestBookDto;
import com.project.libraryms.dto.bookdto.UpdateRequestBookDto;
import com.project.libraryms.entities.Book;
import com.project.libraryms.exception.NotFoundException;
import com.project.libraryms.exception.UnprocessableEntityException;
import com.project.libraryms.serviceimpl.BookServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {


    private final BookServiceImpl bookService;

    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/title/{title}")
    public Optional<Book> getOneBookByName(@PathVariable String title) throws NotFoundException {
        Optional<Book> book = bookService.findBookByTitle(title);
        if (book.isPresent()) {
            return book;
        } else {
            throw new NotFoundException("Book not found with title :" + title);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BookDto> getOneAuthorById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getByBookDtoId(id));

    }

    @GetMapping("/allbook")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }


    @PostMapping("/new")
    public BookDto createNewBook(@RequestBody CreateRequestBookDto createRequestBookDto) {
        if (createRequestBookDto.getTitle() != null && createRequestBookDto.getBarCode() != null) {
            return bookService.createBook(createRequestBookDto);
        } else {
            throw new UnprocessableEntityException("Title & BarCode are required!");
        }
    }


    @PutMapping("/id/{id}")
    public BookDto updateBook(@RequestBody UpdateRequestBookDto updateRequestBookDto, Long id) throws NotFoundException {
        return bookService.updateBook(updateRequestBookDto, id);
    }


//    @DeleteMapping("/id/{id}")
//    public ResponseEntity<?> deleteBookById(@PathVariable Long id) throws NotFoundException {
//        BookDto item = bookService.getByBookDtoId(id);
//        if (item.isPresent()){
//            bookService.deleteBookById(id);
//            return new ResponseEntity<>(new DeleteDetails("Delete request", "Book with id :"+id+" is successfully deleted!"), HttpStatus.OK);
//        }else {
//            throw new NotFoundException("Book not found with id :" + id);
//        }
//    }


}


















