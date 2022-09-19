package com.project.libraryms.mapper;

import com.project.libraryms.dto.authorDto.AuthorDto;
import com.project.libraryms.dto.bookdto.BookDto;
import com.project.libraryms.dto.categoryDto.CategoryDto;
import com.project.libraryms.dto.dto.RentBookDto;
import com.project.libraryms.dto.dto.StockDTO;
import com.project.libraryms.dto.userDto.UserDto;
import com.project.libraryms.entities.Author;
import com.project.libraryms.entities.Book;
import com.project.libraryms.entities.BookRent;
import com.project.libraryms.entities.Category;
import com.project.libraryms.entities.Stock;
import com.project.libraryms.entities.User;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-19T17:05:29+0400",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.13 (Oracle Corporation)"
)
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDto bookEntityToDto(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDto bookDto = new BookDto();

        bookDto.setBarCode( book.getBarCode() );
        bookDto.setTitle( book.getTitle() );
        bookDto.setDescription( book.getDescription() );
        bookDto.setAuthors( authorSetToAuthorDtoSet( book.getAuthors() ) );
        bookDto.setBookRentSet( bookRentSetToRentBookDtoSet( book.getBookRentSet() ) );
        bookDto.setStockSet( stockSetToStockDTOSet( book.getStockSet() ) );
        bookDto.setCategorySet( categorySetToCategoryDtoSet( book.getCategorySet() ) );

        return bookDto;
    }

    @Override
    public Book bookDtoToEntity(BookDto bookDto) {
        if ( bookDto == null ) {
            return null;
        }

        Book book = new Book();

        book.setBarCode( bookDto.getBarCode() );
        book.setTitle( bookDto.getTitle() );
        book.setAuthors( authorDtoSetToAuthorSet( bookDto.getAuthors() ) );
        book.setDescription( bookDto.getDescription() );
        book.setBookRentSet( rentBookDtoSetToBookRentSet( bookDto.getBookRentSet() ) );
        book.setStockSet( stockDTOSetToStockSet( bookDto.getStockSet() ) );
        book.setCategorySet( categoryDtoSetToCategorySet( bookDto.getCategorySet() ) );

        return book;
    }

    @Override
    public List<BookDto> toDto1List(List<Book> books) {
        if ( books == null ) {
            return null;
        }

        List<BookDto> list = new ArrayList<BookDto>( books.size() );
        for ( Book book : books ) {
            list.add( bookEntityToDto( book ) );
        }

        return list;
    }

    protected Set<BookDto> bookSetToBookDtoSet(Set<Book> set) {
        if ( set == null ) {
            return null;
        }

        Set<BookDto> set1 = new LinkedHashSet<BookDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Book book : set ) {
            set1.add( bookEntityToDto( book ) );
        }

        return set1;
    }

    protected AuthorDto authorToAuthorDto(Author author) {
        if ( author == null ) {
            return null;
        }

        AuthorDto authorDto = new AuthorDto();

        authorDto.setFullName( author.getFullName() );
        authorDto.setBirthDate( author.getBirthDate() );
        authorDto.setBooks( bookSetToBookDtoSet( author.getBooks() ) );

        return authorDto;
    }

    protected Set<AuthorDto> authorSetToAuthorDtoSet(Set<Author> set) {
        if ( set == null ) {
            return null;
        }

        Set<AuthorDto> set1 = new LinkedHashSet<AuthorDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Author author : set ) {
            set1.add( authorToAuthorDto( author ) );
        }

        return set1;
    }

    protected UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setFullName( user.getFullName() );
        userDto.setEmail( user.getEmail() );
        userDto.setPassword( user.getPassword() );
        userDto.setBirthDate( user.getBirthDate() );
        userDto.setUsername( user.getUsername() );
        userDto.setAddress( user.getAddress() );

        return userDto;
    }

    protected RentBookDto bookRentToRentBookDto(BookRent bookRent) {
        if ( bookRent == null ) {
            return null;
        }

        RentBookDto rentBookDto = new RentBookDto();

        rentBookDto.setUser( userToUserDto( bookRent.getUser() ) );
        rentBookDto.setConfirmed( bookRent.isConfirmed() );
        rentBookDto.setReturned( bookRent.isReturned() );
        rentBookDto.setId( bookRent.getId() );
        rentBookDto.setCreationDate( bookRent.getCreationDate() );
        rentBookDto.setDueDate( bookRent.getDueDate() );

        return rentBookDto;
    }

    protected Set<RentBookDto> bookRentSetToRentBookDtoSet(Set<BookRent> set) {
        if ( set == null ) {
            return null;
        }

        Set<RentBookDto> set1 = new LinkedHashSet<RentBookDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( BookRent bookRent : set ) {
            set1.add( bookRentToRentBookDto( bookRent ) );
        }

        return set1;
    }

    protected StockDTO stockToStockDTO(Stock stock) {
        if ( stock == null ) {
            return null;
        }

        StockDTO stockDTO = new StockDTO();

        stockDTO.setId( stock.getId() );
        stockDTO.setQuantity( stock.getQuantity() );

        return stockDTO;
    }

    protected Set<StockDTO> stockSetToStockDTOSet(Set<Stock> set) {
        if ( set == null ) {
            return null;
        }

        Set<StockDTO> set1 = new LinkedHashSet<StockDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Stock stock : set ) {
            set1.add( stockToStockDTO( stock ) );
        }

        return set1;
    }

    protected CategoryDto categoryToCategoryDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setCategories( category.getCategories() );
        categoryDto.setId( category.getId() );
        categoryDto.setBook( bookEntityToDto( category.getBook() ) );

        return categoryDto;
    }

    protected Set<CategoryDto> categorySetToCategoryDtoSet(Set<Category> set) {
        if ( set == null ) {
            return null;
        }

        Set<CategoryDto> set1 = new LinkedHashSet<CategoryDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Category category : set ) {
            set1.add( categoryToCategoryDto( category ) );
        }

        return set1;
    }

    protected Set<Book> bookDtoSetToBookSet(Set<BookDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Book> set1 = new LinkedHashSet<Book>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( BookDto bookDto : set ) {
            set1.add( bookDtoToEntity( bookDto ) );
        }

        return set1;
    }

    protected Author authorDtoToAuthor(AuthorDto authorDto) {
        if ( authorDto == null ) {
            return null;
        }

        Author author = new Author();

        author.setFullName( authorDto.getFullName() );
        author.setBirthDate( authorDto.getBirthDate() );
        author.setBooks( bookDtoSetToBookSet( authorDto.getBooks() ) );

        return author;
    }

    protected Set<Author> authorDtoSetToAuthorSet(Set<AuthorDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Author> set1 = new LinkedHashSet<Author>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( AuthorDto authorDto : set ) {
            set1.add( authorDtoToAuthor( authorDto ) );
        }

        return set1;
    }

    protected User userDtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setFullName( userDto.getFullName() );
        user.setUsername( userDto.getUsername() );
        user.setBirthDate( userDto.getBirthDate() );
        user.setAddress( userDto.getAddress() );
        user.setEmail( userDto.getEmail() );
        user.setPassword( userDto.getPassword() );

        return user;
    }

    protected BookRent rentBookDtoToBookRent(RentBookDto rentBookDto) {
        if ( rentBookDto == null ) {
            return null;
        }

        BookRent bookRent = new BookRent();

        bookRent.setCreationDate( rentBookDto.getCreationDate() );
        bookRent.setDueDate( rentBookDto.getDueDate() );
        bookRent.setReturned( rentBookDto.isReturned() );
        bookRent.setUser( userDtoToUser( rentBookDto.getUser() ) );
        bookRent.setId( rentBookDto.getId() );
        bookRent.setConfirmed( rentBookDto.isConfirmed() );

        return bookRent;
    }

    protected Set<BookRent> rentBookDtoSetToBookRentSet(Set<RentBookDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<BookRent> set1 = new LinkedHashSet<BookRent>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RentBookDto rentBookDto : set ) {
            set1.add( rentBookDtoToBookRent( rentBookDto ) );
        }

        return set1;
    }

    protected Stock stockDTOToStock(StockDTO stockDTO) {
        if ( stockDTO == null ) {
            return null;
        }

        Stock stock = new Stock();

        stock.setId( stockDTO.getId() );
        stock.setQuantity( stockDTO.getQuantity() );

        return stock;
    }

    protected Set<Stock> stockDTOSetToStockSet(Set<StockDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Stock> set1 = new LinkedHashSet<Stock>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( StockDTO stockDTO : set ) {
            set1.add( stockDTOToStock( stockDTO ) );
        }

        return set1;
    }

    protected Category categoryDtoToCategory(CategoryDto categoryDto) {
        if ( categoryDto == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryDto.getId() );
        category.setCategories( categoryDto.getCategories() );
        category.setBook( bookDtoToEntity( categoryDto.getBook() ) );

        return category;
    }

    protected Set<Category> categoryDtoSetToCategorySet(Set<CategoryDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Category> set1 = new LinkedHashSet<Category>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( CategoryDto categoryDto : set ) {
            set1.add( categoryDtoToCategory( categoryDto ) );
        }

        return set1;
    }
}
