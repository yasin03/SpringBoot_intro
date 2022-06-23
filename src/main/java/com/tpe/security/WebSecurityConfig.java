package com.tpe.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable().authorizeRequests().
		antMatchers("/","/index.htm","/css/*","/js/*","/images/*").permitAll().
		anyRequest().authenticated().and().httpBasic(); 
		
	}
	
	//$2a$10$zcDL6/94p42zC21RmNvduOfQDJjc9sZc9Rmp10xuB.shbzL5heq1K
	
	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		// UserDetails user1 = User.builder().username("cos").password("{noop}123").roles("ADMIN").build();
		UserDetails userJohn = User.builder().username("john").password(passwordEncoder().encode("coffee")).roles("ADMIN").build();
		UserDetails userDarth = User.builder().username("darth").password(passwordEncoder().encode("vader")).roles("STUDENT").build();
		UserDetails userWalt = User.builder().username("walter").password(passwordEncoder().encode("white")).roles("ADMIN","STUDENT").build();
		
		return new  InMemoryUserDetailsManager(new UserDetails[] {userJohn,userDarth,userWalt});
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}
	
	
}
