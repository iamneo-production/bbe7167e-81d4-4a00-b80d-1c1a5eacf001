package com.virtusa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.model.Role;
import com.virtusa.model.User;
import com.virtusa.repo.RoleRepo;
import com.virtusa.repo.UserRepo;

@Service
public class UserService {
	
	@Autowired
	UserRepo repo;
	
	@Autowired
	RoleRepo rolerepo;
	
	public User findbyusername(String name) {
		return repo.findByUsername(name).get();
	}

	public void registeruser(User user) {
		repo.save(user);
	}
	public Role findbyroleid(Long id) {
		return rolerepo.findById(id).get();
	}

	public List<User> listAllUsers(){
		return repo.findAll();
	}
}
