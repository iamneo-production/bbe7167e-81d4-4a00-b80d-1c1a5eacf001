package com.virtusa.authenticationservice.config;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtusa.authenticationservice.exception.NotFoundException;
import com.virtusa.authenticationservice.model.LoginModel;
import com.virtusa.authenticationservice.model.UserModel;
import com.virtusa.authenticationservice.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private final UserService userService;
	private final Environment environment;
	
	public AuthenticationFilter(AuthenticationManager authManager,UserService userService,Environment environment) {
		super(authManager);
		this.userService=userService;
		this.environment=environment;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req,HttpServletResponse res) throws AuthenticationException{
		try {
			LoginModel creds=new ObjectMapper()
					          .readValue(req.getInputStream(),LoginModel.class);
			
			return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(),new ArrayList<>()));
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest req,HttpServletResponse res,FilterChain chain,Authentication auth) throws IOException,ServletException{
		
		String userName=((User)auth.getPrincipal()).getUsername();
		
			UserModel userCred = userService.findByEmail(userName);
		
		String tokenSecret=environment.getProperty("token.secret");
		byte[] secretKeyBytes=Base64.getEncoder().encode(tokenSecret.getBytes());
		SecretKey secretKey=new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS512.getJcaName());
		Instant now=Instant.now();
		
		String token=Jwts.builder()
				.claim("scope", auth.getAuthorities())
				.setSubject(String.valueOf(userCred.getUserId()))
		.setExpiration(Date.from(now.plusMillis(3600000)))
		.setIssuedAt(Date.from(now))
		.signWith(secretKey,SignatureAlgorithm.HS512)
		.compact();
		
		res.addHeader("token", token);
		res.addHeader("userId", String.valueOf(userCred.getUserId()));
		
	}

}
