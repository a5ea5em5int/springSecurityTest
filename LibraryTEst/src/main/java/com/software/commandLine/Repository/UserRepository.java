package com.software.commandLine.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.software.commandLine.Entity.User;
@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	@Query("select  u from User u where u.email=?1 ")
	public User findUserByEmail(String email);
}
