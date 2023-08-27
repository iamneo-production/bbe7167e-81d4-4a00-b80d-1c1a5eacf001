package com.virtusa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.virtusa.model.User;
import com.virtusa.repo.UserRepo;
import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	 Optional<User> userInfoOptional = repository.findByUsername(username);
         
         User user = userInfoOptional.orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
         
         return new UserInfoUserDetails(user);

    }
}