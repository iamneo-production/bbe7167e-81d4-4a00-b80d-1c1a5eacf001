package com.virtusa.authenticationservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.authenticationservice.model.UserModel;

@Repository
public interface UserRepo extends JpaRepository<UserModel, Long> {

	Optional<UserModel> findByUsername(String username);

	Optional<UserModel> findByEmail(String mail);
	
	boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}