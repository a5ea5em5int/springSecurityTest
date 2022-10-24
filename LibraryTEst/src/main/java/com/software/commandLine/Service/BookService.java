package com.software.commandLine.Service;

import java.util.ArrayList;
import java.util.List;

import com.software.commandLine.Entity.Book;

public interface BookService {
	
	public void saveBook(Book b);
	public List<Book> findALLBook();
}
