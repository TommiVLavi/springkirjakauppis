package com.example.Kirjakauppis.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.Kirjakauppis.domain.Book;
import com.example.Kirjakauppis.domain.BookRepository;
import com.example.Kirjakauppis.domain.CategoryRepository;


@Controller
public class BookController {
	
	@Autowired
	private BookRepository repository;
	@Autowired
	private CategoryRepository catRepository;
	
	
	@RequestMapping(value = {"/", "/booklist"})
	public String list(Model model) {
		model.addAttribute("books", repository.findAll());
		return "listpage";
	}
	
	@RequestMapping(value = "/add")
	public String submit(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", catRepository.findAll());
		return "addbook";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book){
		repository.save(book);
		return "redirect:booklist";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long bookId, Model model) {
		repository.deleteById(bookId);
		return "redirect:../booklist";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", repository.findById(bookId));
		model.addAttribute("categories", catRepository.findAll());
		return "edit";
	}
}
