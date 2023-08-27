package com.virtusa.authenticationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.authenticationservice.exception.NotFoundException;
import com.virtusa.authenticationservice.model.UserModel;
import com.virtusa.authenticationservice.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody UserModel user){
		
		if(userService.existByUsername(user.getUsername()) || userService.existByEmail(user.getEmail())) {
			return ResponseEntity.badRequest().body("Username or email already exists");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userService.registeruser(user);
		return ResponseEntity.ok("User Registered Successfully");
		
	}
	
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserModel> getUserById(@PathVariable Long userId) throws NotFoundException{
		return ResponseEntity.ok(userService.getUserById(userId));
	}
	

}
