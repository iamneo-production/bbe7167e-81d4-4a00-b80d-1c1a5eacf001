package com.virtusa.authenticationservice.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.virtusa.authenticationservice.exception.NotFoundException;
import com.virtusa.authenticationservice.model.UserModel;
import com.virtusa.authenticationservice.repository.RoleRepo;
import com.virtusa.authenticationservice.repository.UserRepo;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	UserRepo repo;
	
	@Autowired
	RoleRepo rolerepo;
	
	public UserModel findbyusername(String name) throws NotFoundException {
		Optional<UserModel> userOptional= repo.findByUsername(name);
		if(userOptional.isEmpty()) {
			throw new NotFoundException("User not found");
		}
		return userOptional.get();
	}
	public UserModel findByEmail(String mail) {
		return repo.findByEmail(mail).get();
	}

	public void registeruser(UserModel user) {
		repo.save(user);
	}
	public UserModel getUserById(Long userid) throws NotFoundException {
		Optional<UserModel> user=repo.findById(userid);
		if(user.isEmpty()) {
			throw new NotFoundException("User not found");
		}
		return user.get();
	}
	
	public List<UserModel> listAllUsers(){
		return repo.findAll();
	}
	
	public boolean existByUsername(String username) {
		return repo.existsByUsername(username);
	}
	public boolean existByEmail(String email) {
		return repo.existsByEmail(email);
	}
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
      Optional<UserModel> userOptional=repo.findByEmail(email);
		
		if(userOptional.isPresent()) {
			UserModel user=userOptional.get();
			
			Collection<GrantedAuthority> authorities=new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
			
			return new User(user.getEmail(), user.getPassword(), true, true, true,true, authorities);
		}
		throw new RuntimeException("user not exist");
	}
}