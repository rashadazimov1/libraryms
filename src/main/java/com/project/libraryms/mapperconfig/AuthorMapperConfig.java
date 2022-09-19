package com.project.libraryms.mapperconfig;

import com.project.libraryms.mapper.AuthorMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthorMapperConfig {
    @Bean
    public AuthorMapper authorMapper() {
        return AuthorMapper.INSTANCE;
    }
}
