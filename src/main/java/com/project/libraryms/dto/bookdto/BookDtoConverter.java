package com.project.libraryms.dto.bookdto;

import com.project.libraryms.entities.Book;
import org.springframework.stereotype.Component;

@Component
public class BookDtoConverter {
    public BookDto bookDtoConverter(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setTitle(book.getTitle());
        bookDto.setDescription(book.getDescription());
        bookDto.setBarCode(book.getBarCode());
        book.setBookRentSet(book.getBookRentSet());
        book.setAuthors(book.getAuthors());
        book.setCategorySet(book.getCategorySet());
        return bookDto;
    }


}
