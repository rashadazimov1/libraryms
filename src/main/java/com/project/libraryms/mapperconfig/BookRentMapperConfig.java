package com.project.libraryms.mapperconfig;

import com.project.libraryms.mapper.BookRentMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookRentMapperConfig {
    @Bean
    public BookRentMapper bookRentMapper() {
        return BookRentMapper.INSTANCE;
    }
}
