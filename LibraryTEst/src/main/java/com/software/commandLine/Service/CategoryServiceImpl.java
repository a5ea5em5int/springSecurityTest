package com.software.commandLine.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software.commandLine.Entity.Book;
import com.software.commandLine.Entity.Category;
import com.software.commandLine.Repository.CategoryRepository;
@Service
public class CategoryServiceImpl implements CategoryService{

	
	@Autowired(required=true)
	private CategoryRepository categoryRepository;
	@Override
	public List<Category> findAllCategories() {
	List<Category> categories =	categoryRepository.findAll();
		return categories;
	}
	
}
