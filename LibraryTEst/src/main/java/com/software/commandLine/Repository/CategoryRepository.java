package com.software.commandLine.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.software.commandLine.Entity.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
	
}
