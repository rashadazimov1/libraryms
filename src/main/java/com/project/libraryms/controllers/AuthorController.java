package com.project.libraryms.controllers;



import com.project.libraryms.entities.Author;
import com.project.libraryms.exception.DeleteDetails;
import com.project.libraryms.exception.NotFoundException;
import com.project.libraryms.exception.UnprocessableEntityException;
import com.project.libraryms.service.impl.AuthorServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {


    private  final AuthorServiceImpl authorService;

    public AuthorController(AuthorServiceImpl authorService) {
        this.authorService = authorService;
    }



    @GetMapping("/id/{id}")
    public Optional<Author> getOneAuthorById(@PathVariable Long id){
        Optional<Author> author =  authorService.getAuthorById(id);
        if (author.isPresent()){
            return author;
        }else {
            throw new NotFoundException("Author not found with id :" + id);
        }
    }



    @GetMapping("/all")
    public Iterable<Author> getAllAuthors(){
        return authorService.getAllAuthors();
    }


    @PostMapping("/new")
    public Author createNewAuthor(@RequestBody Author author) {
        if (author.getBirthDate() != null && author.getFullName() != null){
            if (author.getBirthDate().matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$")){
                return authorService.createAuthor(author);
            }else {
                throw new UnprocessableEntityException("Correct date format is : yyyy-MM-dd");
            }
        }else {
            throw new UnprocessableEntityException("Full name & date of birth are required!");
        }
    }


    @PutMapping("/id/{id}")
    public Author updateAuthor(@RequestBody Author author, @PathVariable Long id) throws NotFoundException {
        return authorService.updateAuthor(author, id);
    }


    @DeleteMapping("/id/{id}")
    public ResponseEntity<DeleteDetails> deleteAuthorById(@PathVariable Long id){
        Optional<Author> author =  authorService.getAuthorById(id);
        if (author.isPresent()){
            authorService.deleteAuthorById(id);
            return new ResponseEntity<>(new DeleteDetails("Delete request", "Author with id :"+id+" is successfully deleted!"), HttpStatus.OK);
        }else {
            throw new NotFoundException("Author not found with id :" + id);
        }
    }



















}
