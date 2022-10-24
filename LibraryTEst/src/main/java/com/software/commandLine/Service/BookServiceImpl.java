package com.software.commandLine.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.commandLine.Entity.Book;
import com.software.commandLine.Repository.BookInterface;
@Service
public class BookServiceImpl implements BookService{
	@Autowired
	private BookInterface binterface;
	@Override
	public void saveBook(Book b) {
		// TODO Auto-generated method stub
		binterface.save(b);
	}
	@Override
	public List<Book> findALLBook() {
		// TODO Auto-generated method stub
		return binterface.findAll();
	}
		
}
