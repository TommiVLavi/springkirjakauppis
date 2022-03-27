package com.example.Kirjakauppis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Kirjakauppis.domain.Book;
import com.example.Kirjakauppis.domain.BookRepository;
import com.example.Kirjakauppis.domain.Category;
import com.example.Kirjakauppis.domain.CategoryRepository;
import com.example.Kirjakauppis.domain.User;
import com.example.Kirjakauppis.domain.UserRepository;

import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class KirjakauppisApplication {

	public static void main(String[] args) {
		SpringApplication.run(KirjakauppisApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository catRepository,
			UserRepository usrRepository) {
		return (args) -> {
			// Your code...add some demo data to db
			
			catRepository.save(new Category("Satire"));
			catRepository.save(new Category("Art"));
			catRepository.save(new Category("Travel"));
			
			repository.save(new Book(catRepository.findByName("Satire").get(0), "Animal Farm","George Orwell", 1940, 45345, 10.8));
			repository.save(new Book(catRepository.findByName("Art").get(0),"The Animators Survival Kit", "Richard Williams", 2000, 453975, 34.9));
			repository.save(new Book(catRepository.findByName("Travel").get(0),"A visit to Tallinn", "Eesti Keesti", 2021, 437553, 12.3));
			
			usrRepository.save(new User("Tommi",
					"$2a$10$zWOvOn/XXhVC37jfEYDdfOwvJJqEIxeg/FLNCZT/AEGFdQR8niVXi",
					"Bobytom62@gmail.com","USER"));
			usrRepository.save(new User("Santeri",
					"$2a$10$UOYUmeKkuT6/SwedGF9eH.HupsEJM7JXG9kxyaM4wWwa9xWeae5B6",
					"bgr912@myy.haaga-helia.fi","ADMIN"));
		};
	}
}
