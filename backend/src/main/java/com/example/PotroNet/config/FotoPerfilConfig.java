package com.example.PotroNet.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FotoPerfilConfig implements WebMvcConfigurer {
   @Bean
    public Cloudinary cloudinary(){
       return new Cloudinary(System.getenv("CLOUDINARY_URL"));
   }
}
