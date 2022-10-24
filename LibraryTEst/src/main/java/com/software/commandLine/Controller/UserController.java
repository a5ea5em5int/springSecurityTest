package com.software.commandLine.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.software.commandLine.Entity.User;

import com.software.commandLine.Repository.UserDetailServiceImipl;
import com.software.commandLine.Repository.UserRepository;


@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired(required=true)
	private UserDetailServiceImipl userService;
	
	
	@GetMapping("/register")
	public String showRegisterForm(Model model)
	{		model.addAttribute("user",new User());
			
			model.addAttribute("title","register form");
			
		return "register";
	}
	@PostMapping("/registerProcess")
	public String register(User user,RedirectAttributes ra,@RequestParam(name="role")String role)
	{	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encoded = encoder.encode(user.getPassword());
		user.setPassword(encoded);
		user.setRole(role);
		userRepository.save(user);
		ra.addFlashAttribute("regSuccess","Register Success"+user.getName());
		return "redirect:/login";
	}
	@GetMapping("/login")
	public String showLoginForm(Model model)
	{	model.addAttribute("user", new User());
		return "login";
	}
	@PostMapping("/loginProcess")
	public String login(User user, RedirectAttributes ra)
	{	
		UserDetails user1 = userService.loadUserByUsername(user.getEmail());
		if (user1==null) 
		{	ra.addFlashAttribute("fail1", "User does not exist");
			System.out.println("hello no user exists");
			return "redirect:/login";
		}
		else { System.out.println("in user email=> "+ user.getEmail());
			
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				if (encoder.matches(user.getPassword(),user1.getPassword()))
				
					{ 	System.out.println(user1.getAuthorities());
						System.out.println("hello password matches in login");
						
						return "redirect:/books";}
				else {	System.out.println("wrong pasword");
						ra.addFlashAttribute("fail2", "User name or Password does not match");
						return "redirect:/login";
					}
			}
	}
	
	
}
