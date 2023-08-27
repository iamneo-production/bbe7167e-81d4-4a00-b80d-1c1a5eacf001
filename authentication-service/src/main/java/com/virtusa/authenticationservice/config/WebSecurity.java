package com.virtusa.authenticationservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.virtusa.authenticationservice.service.UserService;


@Configuration
@EnableWebSecurity
public class WebSecurity {
	
	private final UserService userService;
	private final Environment environment;
	
	public WebSecurity(UserService userService,Environment environment) {
		this.userService=userService;
		this.environment=environment;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@SuppressWarnings("removal")
	@Bean
	protected SecurityFilterChain configure(HttpSecurity http )throws Exception{
		
		AuthenticationManagerBuilder authManagerBuilder=http.getSharedObject(AuthenticationManagerBuilder.class);
		authManagerBuilder.userDetailsService(userService)
		.passwordEncoder(passwordEncoder());
		
		AuthenticationManager authManager=authManagerBuilder.build();
		AuthenticationFilter authFilter=new AuthenticationFilter(authManager,userService,environment);
		authFilter.setFilterProcessesUrl("/api/auth/login");
		http.csrf().disable(); 
		http.authorizeHttpRequests()
		    .requestMatchers("/api/auth/register","/api/auth/login").permitAll()
		    .requestMatchers("/api/auth/{userId}").hasRole("EDITOR")
		    //.requestMatchers("/auth/create").permitAll()
		  //  .requestMatchers("/login").permitAll()
		    .anyRequest().authenticated()
		    .and()
		    .addFilter(new AuthorizationFilter(authManager,environment))
		    .addFilter(authFilter)
		    .authenticationManager(authManager)
		    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		return http.build();
	}
}
