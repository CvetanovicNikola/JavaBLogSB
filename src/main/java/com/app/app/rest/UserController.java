package com.app.app.rest;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.app.app.exceptions.UserNotFoundException;
import com.app.app.model.User;
import com.app.app.repository.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	
	@PostMapping("/users")
	public User addUser(@RequestBody @Valid User user) {
		return userRepository.save(user);
		
	}
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userRepository.findAll();
		
	}
	
	@GetMapping("/users/{userId}")
	public User getUserById (@PathVariable int userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("No such user!"));
				
	}
	
	@PutMapping("users/{userId}")
	public User updateUser(@PathVariable int userId, @RequestBody User user) {
		return userRepository
				.findById(userId)
				.map(u ->{
					u.setName(user.getName());
					u.setLastname(user.getLastname());
					u.setPassword(user.getPassword());
					u.setUsername(user.getUsername());
					u.setEmail(user.getEmail());
					u.setDate(user.getDate());
					return userRepository.save(u);
					}).orElseThrow(() -> new UserNotFoundException("No such user!"));
	}
	
	@DeleteMapping("/users/{userId}")
	public void deleteUser(@PathVariable int userId) {
		userRepository.delete(userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("No such user!")));
		
	}
	
	@GetMapping("/user/{username}")
	public User getUserByUsername(@PathVariable String username){
		return userRepository.findAll().stream()
				.filter(u -> u.getUsername().equals(username))
				.findFirst()
				.orElseThrow(() -> new UserNotFoundException("No such user"));
		
	}

}
