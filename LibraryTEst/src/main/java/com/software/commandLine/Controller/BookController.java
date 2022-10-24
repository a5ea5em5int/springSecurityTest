package com.software.commandLine.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.software.commandLine.Entity.Book;
import com.software.commandLine.Entity.Category;
import com.software.commandLine.Entity.User;
import com.software.commandLine.Repository.BookInterface;
import com.software.commandLine.Service.BookService;
import com.software.commandLine.Service.BookServiceImpl;
import com.software.commandLine.Service.CategoryServiceImpl;

@Controller
public class BookController {
	@Autowired(required=true)
	private BookServiceImpl bservice;
	@Autowired
	private CategoryServiceImpl categoryService;
	@GetMapping("/books")
	public String showBooks(Book book, Model model)
	{	
		List<Book> books = bservice.findALLBook();
		model.addAttribute("books", books);
		return "books";
	}
	@PostMapping("/books/add")
	public String addBook(@Valid Book book, BindingResult result, RedirectAttributes ra,final SessionStatus sessionStatus)
	{	
		if(result.hasErrors())
			{// to show field errors in theymeleaf 
			ra.addFlashAttribute("org.springframework.validation.BindingResult.book", result);
		    ra.addFlashAttribute("book", book);
			return "redirect:/books/new";}
		ra.addFlashAttribute("in_succsess", "Insert success");
		bservice.saveBook(book);
		
		return "redirect:/books";
	}
	@GetMapping("/books/new")
	public String insertBook( Model model)
	{	if (!model.containsAttribute("book"))
					{
						model.addAttribute("book", new Book());
					}
		List<Category> categories = categoryService.findAllCategories();
		System.out.println(categories);
		model.addAttribute("categories", categories);
		return "book";
	}

}
