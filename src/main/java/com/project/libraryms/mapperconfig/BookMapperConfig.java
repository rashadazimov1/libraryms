package com.project.libraryms.mapperconfig;

import com.project.libraryms.mapper.BookMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BookMapperConfig {
    @Bean
    public BookMapper bookMapper() {
        return BookMapper.INSTANCE;
    }
}
