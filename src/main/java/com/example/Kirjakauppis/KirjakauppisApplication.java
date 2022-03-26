package com.example.Kirjakauppis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Kirjakauppis.domain.Book;
import com.example.Kirjakauppis.domain.BookRepository;
import com.example.Kirjakauppis.domain.Category;
import com.example.Kirjakauppis.domain.CategoryRepository;

import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class KirjakauppisApplication {

	public static void main(String[] args) {
		SpringApplication.run(KirjakauppisApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository catRepository) {
		return (args) -> {
			// Your code...add some demo data to db
			
			catRepository.save(new Category("Satire"));
			catRepository.save(new Category("Art"));
			catRepository.save(new Category("Travel"));
			
			repository.save(new Book(catRepository.findByName("Satire").get(0), "Animal Farm","George Orwell", 1940, 45345, 10.8));
			repository.save(new Book(catRepository.findByName("Art").get(0),"The Animators Survival Kit", "Richard Williams", 2000, 453975, 34.9));
			repository.save(new Book(catRepository.findByName("Travel").get(0),"A visit to Tallinn", "Eesti Keesti", 2021, 437553, 12.3));
			
		};
	}
}
