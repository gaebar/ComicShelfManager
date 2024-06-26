package com.example.ComicShelfManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication // This annotation includes @ComponentScan, @EnableAutoConfiguration, and @Configuration
@ComponentScan("com.example") //Spring will look for Beans within com.example (and its subpackages)
@EntityScan("com.example.models") // Specifies the directory for JPA entities to facilitate Spring's scanning
@EnableJpaRepositories("com.example.daos") // Enables JPA repositories in the specified directory

public class ComicShelfManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComicShelfManagerApplication.class, args);
	}
}
