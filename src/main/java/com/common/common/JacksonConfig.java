//package com.common.common;
//
//import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//
//@Configuration
//public class JacksonConfig {
//	
//	 @Bean
//	    public Jackson2ObjectMapperBuilderCustomizer customizeJackson() {
//	        return jacksonObjectMapperBuilder -> {
//	            jacksonObjectMapperBuilder.modules(new JavaTimeModule()); // Configure JavaTimeModule for LocalDate serialization
//	        };
//	    }
//}
