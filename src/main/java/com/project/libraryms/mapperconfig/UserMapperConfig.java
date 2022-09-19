package com.project.libraryms.mapperconfig;

import com.project.libraryms.mapper.UserMapper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserMapperConfig {
    @Bean
    public UserMapper userMapper() {
        return UserMapper.INSTANCE;
    }

}
