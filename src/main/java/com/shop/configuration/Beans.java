package com.shop.configuration;


import com.shop.data.services.Picture.PictureOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//(defaultAutowire = Autowire.BY_TYPE, defaultLazy = Lazy.FALSE)
public class Beans {
    @Bean
    public PictureOperations pictureOperations() {
        return new PictureOperations();
    }
}
