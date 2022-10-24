package com.software.commandLine.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.software.commandLine.Entity.User;

@Service
public class UserDetailServiceImipl implements UserDetailsService {
		@Autowired
		private UserRepository userrepo;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user =	userrepo.findUserByEmail(email);
		if (user == null) throw new UsernameNotFoundException(email);
		return new MyUserDetail(user);
	}

}
