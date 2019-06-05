package com.app.app.rest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
//		if(result.hasErrors()) {
//			throw new ConstraintViolationException(null);
//		}
		userRepository.save(user);
		return user;
		//return new ResponseEntity<User> (user, HttpStatus.OK) ;
	}
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userRepository.findAll();
		
	}
	@GetMapping("/users/{id}")
	public Optional<User> getUserById (@PathVariable int id) {
		Optional<User> user= userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("user with id " + id + " not found");
		}
		return user;
	}
	@PutMapping("users/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User user) {
		Optional<User> userFromDb = userRepository.findById(id);
		if(userFromDb.isPresent()) {
			user.setId(userFromDb.get().getId());
				
		}
		userRepository.save(user);
		return user;
	}
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	@GetMapping("/user/{username}")
	public User getUserByUsername(@PathVariable String username){
		List<User> allUsers = userRepository.findAll();
		Optional<User> user = allUsers.stream()
				.filter(u -> u.getUsername().equals(username))
				.findFirst();
		if(!user.isPresent()) {
			throw new UserNotFoundException("No such user");
		}
		return user.get();
	}
	

}
