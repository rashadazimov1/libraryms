package com.project.libraryms.serviceimpl;

import com.project.libraryms.dto.authorDto.AuthorDto;
import com.project.libraryms.dto.authorDto.AuthorDtoConverter;
import com.project.libraryms.dto.authorDto.CreateRequestAuthorDto;
import com.project.libraryms.dto.authorDto.UpdateRequestAuthorDto;
import com.project.libraryms.entities.Author;
import com.project.libraryms.exception.NotFoundException;
import com.project.libraryms.mapper.AuthorMapper;
import com.project.libraryms.repos.AuthorRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class AuthorServiceImpl {


    private final AuthorRepository authorRepository;
    private final AuthorDtoConverter authorDtoConverter;
    private final AuthorMapper authorMapper;


    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorDtoConverter authorDtoConverter, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorDtoConverter = authorDtoConverter;
        this.authorMapper = authorMapper;
    }


    public AuthorDto createAuthor(CreateRequestAuthorDto createRequestAuthorDto) {
        Author author = new Author();
        author.setFullName(createRequestAuthorDto.getFullName());
        author.setBirthDate(createRequestAuthorDto.getBirthDate());
        author.setBooks(author.getBooks());
        authorRepository.save(author);

        return authorDtoConverter.authorDtoConverter(author);
    }

    public void deleteAuthorById(Long id) {
        Optional<Author> foundAuthor = authorRepository.findById(id);
        authorRepository.deleteById(foundAuthor.get().getId());
    }

    public AuthorDto getByAuthorDtoId(Long id) {
        Optional<Author> userOptional = authorRepository.findById(id);
        return userOptional.map(authorMapper::authorEntityToDto).orElse(new AuthorDto());

    }


    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<AuthorDto> getAllAuthor() {
        List<Author> authorList = (List<Author>) authorRepository.findAll();
        return authorMapper.toDtoList(authorList);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Iterable<Author> findAuthorByItemsId(Long id) {
        return authorRepository.findAuthorByItems_Id(id);
    }

    @CacheEvict(value = "book", allEntries = true)
    public AuthorDto updateAuthor(UpdateRequestAuthorDto updateRequestAuthorDto, Long id) throws NotFoundException {
        Optional<Author> authorOptional = authorRepository.findById(id);
        authorOptional.ifPresent(author -> {
            author.setFullName(updateRequestAuthorDto.getFullName());
            author.setBirthDate(updateRequestAuthorDto.getBirthDate());
            authorRepository.save(author);
        });
        return authorOptional.map(authorDtoConverter::authorDtoConverter).orElse(new AuthorDto());
    }
}