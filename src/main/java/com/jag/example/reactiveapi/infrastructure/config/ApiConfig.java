package com.jag.example.reactiveapi.infrastructure.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * @Configuration: Indicates that a class declares one or more @Bean
 * methods and may be processed by the Spring container to generate bean definitions
 * and service requests for those beans at runtime
 */
@Configuration
@EnableWebFlux
public class ApiConfig {
    /**
     * Configuration to transform a Json Object into a java String and vice versa
     * @return
     *   @Bean: To be instantiated or added in
     */
    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        // To model only the required fields from the JSON
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);//To write date times in human
        // readable format
        return objectMapper;
    }


    /**
     *
     * @param objectMapper
     * @return pretty/beautiful format
     */
    @Bean
    public ObjectWriter objectWriter(ObjectMapper objectMapper){
        return objectMapper.writerWithDefaultPrettyPrinter();
    }
}
