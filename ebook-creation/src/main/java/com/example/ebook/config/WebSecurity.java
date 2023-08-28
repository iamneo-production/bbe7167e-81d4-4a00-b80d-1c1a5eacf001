package com.example.ebook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurity {
	
	private final Environment environment;
	
	public WebSecurity(Environment environment) {
		this.environment=environment;
	}

	@Bean
	protected SecurityFilterChain configure(HttpSecurity http )throws Exception{
		
		AuthenticationManagerBuilder authManagerBuilder=http.getSharedObject(AuthenticationManagerBuilder.class);
		
		
		AuthenticationManager authManager=authManagerBuilder.build();
		
		http.csrf().disable(); 
		http.authorizeHttpRequests()
		    .requestMatchers(HttpMethod.POST, "/api/ebook").hasRole("AUTHOR")
		    .requestMatchers(HttpMethod.PUT, "/api/ebook").hasAnyRole("AUTHOR","EDITOR","DESIGNER")
		    //.requestMatchers("/auth/create").permitAll()
		  //  .requestMatchers("/login").permitAll()
		    .anyRequest().authenticated()
		    .and()
		    .addFilter(new AuthorizationFilter(authManager,environment))
		    
		    .authenticationManager(authManager)
		    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		return http.build();
	}
}
