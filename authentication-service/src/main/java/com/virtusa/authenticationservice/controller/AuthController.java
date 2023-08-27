package com.virtusa.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.exception.RoleNotFoundException;
import com.virtusa.model.AuthRequest;
import com.virtusa.model.Role;
import com.virtusa.model.User;
import com.virtusa.service.JwtService;
import com.virtusa.service.UserService;

@RestController
@RequestMapping("/authapi")
public class AuthController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody User user) throws RoleNotFoundException{
		
		try {
		if(service.findbyusername(user.getUsername())!=null) {
			return ResponseEntity.badRequest().body("Username already exists");
		}
		System.out.println(user);
		return ResponseEntity.ok("User Registered doen't registered ");
		}
		catch (NoSuchElementException e) {
			 Role userRole = service.findbyroleid(user.getRole().getId());
			    if (userRole == null) {
			    	throw new RoleNotFoundException("Invalid role ID");
			    }
			    user.setRole(userRole);
			 // Encode the password before saving
			    String encodedPassword = passwordEncoder.encode(user.getPassword());
			    user.setPassword(encodedPassword);

			    service.registeruser(user);
				return ResponseEntity.ok("User Registered Successfully");
			
		}
	}
	
	@PostMapping("/authenticate")
	public String authenticateUser(@RequestBody AuthRequest authRequest){
		
		System.out.println(authRequest);
		
		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if(authentication.isAuthenticated()) {
			return jwtService.generateToken(authRequest.getUsername());
		}
		else {
			throw new UsernameNotFoundException("invalid User Request");
		}
		
	}
	
	@GetMapping("/hi")
	public String sayHi() {
		return "Hello World";
	}
	
	@GetMapping("/{id}")
	public Role getRoleById(@PathVariable("id") Long id) {
		return service.findbyroleid(id);
	}
	
	@GetMapping("/allusers")
	public List<User> getAllUSers(){
		return service.listAllUsers();
	}

}
