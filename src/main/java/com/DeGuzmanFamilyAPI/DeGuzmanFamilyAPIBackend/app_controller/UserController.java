package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.Users;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/app/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@CrossOrigin
	@GetMapping("/all")
	public List<Users> getAllUserInformation() {
		return userService.findAllUsers();
	}
	
	@CrossOrigin
	@GetMapping("/user/{userid}") 
	public ResponseEntity<Optional<Users>> getUserById(@PathVariable Long userid) {
		return userService.findUserById(userid);
	}
	
	@CrossOrigin
	@GetMapping("/pending-users")
	public List<Users> getAllPendingUsers() {
		return userService.findPendingUsers();
	}
	
	@CrossOrigin
	@PostMapping("/add-user")
	public Users addUser(@Valid @RequestBody Users user) {
		return userService.addUser(user);
	}
	
	@CrossOrigin
	@PutMapping("/user/{userid}")
	public ResponseEntity<Users> updateUser(@PathVariable Long userid,
			@Valid @RequestBody Users userDetails) {
		return userService.updateUser(userid, userDetails);
	}
	
	@CrossOrigin
	@DeleteMapping("/user/{userid}")
	public Map<String,Boolean> deleteUser(@PathVariable Long userid) {
		return userService.deleteUser(userid);
	}
	
	@CrossOrigin
	@DeleteMapping("delete-all")
	public Map<String,Boolean>deleteAllUsers() {
		return userService.deleteAllUsers();
	}
}
