package com.project.libraryms.mapperconfig;

import com.project.libraryms.mapper.StockMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StockMapperConfig {
    @Bean
    public StockMapper stockMapper() {
        return StockMapper.INSTANCE;
    }
}
