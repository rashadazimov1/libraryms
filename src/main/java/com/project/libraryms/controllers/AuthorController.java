package com.project.libraryms.controllers;



import com.project.libraryms.dto.authorDto.AuthorDto;
import com.project.libraryms.dto.authorDto.CreateRequestAuthorDto;
import com.project.libraryms.dto.authorDto.UpdateRequestAuthorDto;
import com.project.libraryms.exception.NotFoundException;
import com.project.libraryms.exception.UnprocessableEntityException;
import com.project.libraryms.serviceimpl.AuthorServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {


    private final AuthorServiceImpl authorService;

    public AuthorController(AuthorServiceImpl authorService) {
        this.authorService = authorService;
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<AuthorDto> getOneAuthorById(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.getByAuthorDtoId(id));

    }


//    public void getAuthors(Long id){
//        Iterable<Author> iterable = authorService.findAuthorByItemsId(id);
//        authorList = StreamSupport.stream(iterable.spliterator(), false)
//                .collect(Collectors.toList());
//    }
//
//    @GetMapping("/idauthorbook/{id}")
//    public Optional<Author> getOneAuthorByBookName(@PathVariable Long id) throws NotFoundException {
//        Iterable<Author> author = authorService.findAuthorByItemsId(id);
//        if (author.isPresent()){
//            return author;
//        }else {
//            throw new NotFoundException("Book not found with id :" + id);
//        }
//    }

    @GetMapping("/all")
    public ResponseEntity<List<AuthorDto>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthor());
    }


    @PostMapping("/new")
    public AuthorDto createNewAuthor(@RequestBody CreateRequestAuthorDto createRequestAuthorDto) {
        if (createRequestAuthorDto.getBirthDate() != null && createRequestAuthorDto.getFullName() != null) {
            if (createRequestAuthorDto.getBirthDate().matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$")) {
                return authorService.createAuthor(createRequestAuthorDto);
            } else {
                throw new UnprocessableEntityException("Correct date format is : yyyy-MM-dd");
            }
        } else {
            throw new UnprocessableEntityException("Full name & date of birth are required!");
        }
    }


    @PutMapping("/id/{id}")
    public AuthorDto updateAuthor(@RequestBody UpdateRequestAuthorDto updateRequestAuthorDto, @PathVariable Long id) throws NotFoundException {
        return authorService.updateAuthor(updateRequestAuthorDto, id);
    }
}

//    @DeleteMapping("/id/{id}")
//    public ResponseEntity<DeleteDetails> deleteAuthorById(@PathVariable Long id){
//        AuthorDto author =  authorService.getByAuthorDtoId(id);
//        if (author.){
//            authorService.deleteAuthorById(id);
//            return new ResponseEntity<>(new DeleteDetails("Delete request", "Author with id :"+id+" is successfully deleted!"), HttpStatus.OK);
//        }else {
//            throw new NotFoundException("Author not found with id :" + id);
//        }
//    }




















