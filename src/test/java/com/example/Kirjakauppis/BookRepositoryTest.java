package com.example.Kirjakauppis;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.Kirjakauppis.domain.Category;
import com.example.Kirjakauppis.domain.Book;
import com.example.Kirjakauppis.domain.BookRepository;
import com.example.Kirjakauppis.domain.CategoryRepository;
import com.example.Kirjakauppis.domain.User;
import com.example.Kirjakauppis.domain.UserRepository;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {
	
	//BOOKREP
	@Autowired
	private BookRepository bookRep;
	
	@Test
	public void searchBookTest() {
		List<Book> bunch = bookRep.findByTitle("Animal Farm");
		assertThat(bunch.get(0).getAuthor()).isEqualTo("George Orwell");
	}
	
	@Test
	public void saveBookTest() {
		Book book = new Book(catRep.findByName("Travel").get(0),
				"Testing", "Test", 2021, 437553, 12.3);
		bookRep.save(book);
		assertThat(bookRep.findByTitle("Testing").get(0).getId()).isNotNull();
	}
	
	@Test
	public void deleteBookTest() {
		List<Book> bunch = bookRep.findByTitle("The Animators Survival Kit");
		Long id = bunch.get(0).getId();
		bookRep.deleteById(id);
		assertThat(bookRep.findById(id)).isEmpty();
	}
	
	
	//USERREP
	@Autowired
	private UserRepository userRep;
	
	@Test
	public void searchUserTest() {
		User user = userRep.findByUsername("Tommi");
		assertThat(user.getEmail()).isEqualTo("Bobytom62@gmail.com");
	}
	
	@Test
	public void saveUserTest() {
		User user = new User("Kalev",
				"$2a$10$UOYUmeKkuT6/SwedGF9eH.HupsEJM7JXG9kxyaM4wWwa9xWeae5B6",
				"bgr912@myy.haaga-helia.fi","ADMIN");
		userRep.save(user);
		assertThat(userRep.findByUsername("Kalev").getId()).isNotNull();
	}
	
	@Test
	public void deleteUserTest() {
		User user = userRep.findByUsername("Tommi");
		Long id = user.getId();
		userRep.deleteById(id);
		assertThat(bookRep.findById(id)).isEmpty();
	}
	
	
	//CATEGORYREP
	@Autowired
	private CategoryRepository catRep;
	
	@Test
	public void searchCatTest() {
		List<Category> bunch = catRep.findByName("Art");
		assertThat(bunch.get(0).getName()).isEqualTo("Art");
	}
	
	@Test
	public void saveCatTest() {
		Category cat = new Category("Tutorial");
		catRep.save(cat);
		assertThat(catRep.findByName("Tutorial").get(0).getCategoryid()).isNotNull();
	}
	
	@Test
	public void deleteCat() {
		List<Category> bunch = catRep.findByName("Art");
		Long id = bunch.get(0).getCategoryid();
		catRep.deleteById(id);
		assertThat(catRep.findById(id)).isEmpty();
	}
}
