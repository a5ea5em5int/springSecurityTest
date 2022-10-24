
package com.software.commandLine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.software.commandLine.Repository.UserDetailServiceImipl;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {
	@Bean
	public UserDetailsService userDetailsService() {
		System.out.println("in************%%%%%userdetail");
		return new UserDetailServiceImipl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		System.out.println("in************%%%%% password encoder");
		return new BCryptPasswordEncoder();
	}
	

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		System.out.println("in************%%%%% authentication provider");
		return authProvider;
	}


	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("in************%%%%% authentication config");
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		/*
		 * http. csrf(csrf-> csrf.disable()) .authorizeRequests()
		 * 
		 * .antMatchers("/books/**","/books/new").hasRole("ADMIN")
		 * .mvcMatchers("/register","/login","/registerProcess","/loginProcess").
		 * permitAll()
		 * .and().formLogin().loginPage("/login").usernameParameter("email");
		 */
		/*
		 * http .authorizeRequests() .antMatchers("/books/new").hasAuthority("ADMIN")
		 * .antMatchers("/login*","register","registerProcess","loginProcess")
		 * .permitAll() .anyRequest() .authenticated() .and()
		 * .formLogin().loginPage("/login") .successHandler(new
		 * RefererRedirectionAuthenticationSuccessHandler());
		 */
		
		http.authorizeRequests()
		.antMatchers("/books/**").hasAnyAuthority("ADMIN")
		.antMatchers("/register","/registerProcess","/login","/loginProcess").permitAll()
	.and()	.formLogin(form -> form
			.loginPage("/login")
			.permitAll()
		);
	    return http.build();
         
		
		
	
	}
	}
