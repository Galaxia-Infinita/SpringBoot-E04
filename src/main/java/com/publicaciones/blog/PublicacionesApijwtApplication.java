package com.publicaciones.blog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PublicacionesApijwtApplication {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();   //para no escribir todo el codigo de mapeo
	}
	
	public static void main(String[] args) {
		SpringApplication.run(PublicacionesApijwtApplication.class, args);
	}

}
