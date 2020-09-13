package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.authentication_service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.Lookup_Values.RoleValues;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.Lookup_Values.UserStatusValues;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.Users;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepository.findUserByUsername(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
	}
	
	
	public Users save(Users user) {
		Users newUser = new Users();
		UserDetails userExists = null;
		if (userExists == loadUserByUsername(user.getUsername())) {
			System.out.println("yo this person is here already");
		}
		
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setRoleid(RoleValues.DEGUZMANSTUFFANYWHERE_BASIC_USER);
		newUser.setUser_status(UserStatusValues.DEGUZMANSTUFFANYWHERE_PENDING);
		return userRepository.save(newUser);
	}
}
