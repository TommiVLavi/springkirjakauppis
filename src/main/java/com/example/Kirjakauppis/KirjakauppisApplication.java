package com.example.Kirjakauppis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

import com.example.Kirjakauppis.domain.Book;
import com.example.Kirjakauppis.domain.BookRepository;
import com.example.Kirjakauppis.domain.Category;
import com.example.Kirjakauppis.domain.CategoryRepository;
import com.example.Kirjakauppis.domain.User;
import com.example.Kirjakauppis.domain.UserRepository;

@SpringBootApplication
public class KirjakauppisApplication {
	private static final Logger log = LoggerFactory.getLogger(KirjakauppisApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(KirjakauppisApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository catRepository,
			UserRepository usrRepository) {
		return (args) -> {
			// Your code...add some demo data to db
			log.info("save few books");
			
			catRepository.save(new Category("Satire"));
			catRepository.save(new Category("Art"));
			catRepository.save(new Category("Travel"));
			
			repository.save(new Book(catRepository.findByName("Satire").get(0), "Animal Farm","George Orwell", 1940, 45345, 10.8));
			repository.save(new Book(catRepository.findByName("Art").get(0),"The Animators Survival Kit", "Richard Williams", 2000, 453975, 34.9));
			repository.save(new Book(catRepository.findByName("Travel").get(0),"A visit to Tallinn", "Eesti Keesti", 2021, 437553, 12.3));
			
			//1234
			User user1 = new User("Tommi",
					"$2y$10$oo5hH5pLKXVZC/EXo6mPL.G1HGhffNqtgUk44MHgGFyN5k7fnPIJu",
					"Bobytom62@gmail.com","USER");
			
			usrRepository.save(user1);
			
			//4321
			User user2 = new User("Santeri",
					"$2y$10$MPHAZzAmMTk4bhgd.dpI0uQVhpAbsHdIndxRSXidb0I49DNcsVF7K",
					"bgr912@myy.haaga-helia.fi","ADMIN");
			
			usrRepository.save(user2);
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
		};
	}
}
