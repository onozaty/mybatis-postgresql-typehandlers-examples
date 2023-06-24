package com.github.onozaty.mybatis.pg.example;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.onozaty.mybatis.pg.example.domain.JsonData;
import com.github.onozaty.mybatis.pg.type.json.JsonTypeHandler;

@Configuration
public class MyBatisConfiguration {

    @Bean
    ConfigurationCustomizer mybatisConfigurationCustomizer() {
        return (configuration) -> {
            configuration.getTypeHandlerRegistry().register(JsonData.class, JsonTypeHandler.class);
        };
    }
}
