package com.software.commandLine.Controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.software.commandLine.Entity.Book;

@Controller
public class TestController {
	@GetMapping("/showBooks")
	public String showEmp(Model model)
	{
		ArrayList<Book> books= new ArrayList<Book>();
		Book b1=new Book();Book b2=new Book();Book b3=new Book();
		b1.setId(1);
		b1.setTitle("Java programming");
		b1.setDescription("It is designed for students");
		b1.setPrice(900);
		books.add(b1);
		b2.setId(2);
		b2.setTitle("Data collection programming");
		b2.setPrice(230);
		b2.setDescription("It is suited for professionals");
		books.add(b2);
		
		b3.setId(3);
		b3.setTitle("Scalar programming");
		b3.setPrice(130);
		b3.setDescription("It is suited for novices");
		books.add(b3);
		model.addAttribute("books", books);
		model.addAttribute("title", "for first day teaching test");
		return "books2";
		
	}

}
