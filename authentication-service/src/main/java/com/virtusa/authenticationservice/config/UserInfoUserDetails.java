package com.virtusa.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.virtusa.model.User;

public class UserInfoUserDetails implements UserDetails{
	
	 private String name;
	 private String password;
	 private List<GrantedAuthority> authorities;
	 
	 public UserInfoUserDetails(User user) {
	        name=user.getUsername();
	        password=user.getPassword();
//	        authorities= Arrays.stream(userInfo.getRoles().split(","))
//	                .map(SimpleGrantedAuthority::new)
//	                .collect(Collectors.toList());
	    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
