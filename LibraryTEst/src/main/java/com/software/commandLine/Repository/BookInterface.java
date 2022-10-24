package com.software.commandLine.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.software.commandLine.Entity.Book;
@Repository
public interface BookInterface extends JpaRepository<Book,Integer>{

}
