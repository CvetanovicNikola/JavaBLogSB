package com.app.app.JSF;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.app.app.exceptions.UserNotFoundException;
import com.app.app.model.User;
import com.app.app.rest.UserController;
import lombok.Data;



@Named("user")
@SessionScoped
@Data
public class UserBean implements Serializable{
		
	@Autowired
	private UserController userController;
	
	private String name;
	private String lastname;
	private String username;
	private String password;
	private String email;
	private String errorMessage;
	private String userId;
	private User user ;
	private String message;
	private String usernameLogin;
	private String passwordLogin;
	private User loggedUser;
	
	
	public String userLogin() {
		try {
			User u = userController.getUserByUsername(usernameLogin);
			if (u.getPassword().equals(passwordLogin)) {
				message = "You have logged in.";
				loggedUser = u;
				System.out.println(loggedUser.getName());
				return "indexLogged.xhtml?faces-redirect=true";
			}
			else {
				message = "Invalid password";
				return "loginFailed?faces-redirect=true";
			}
			
		}
		catch (UserNotFoundException e){
			message = e.getMessage();
			//System.out.println(message);
			return "loginFailed?faces-redirect=true";
		}
		
	}
	
		
	public User getUserById() {
		int id = Integer.valueOf(userId);
		return userController.getUserById(id);
	}
	
	public String createUser() {
		

		User u = new User();
		
		u.setUsername(username); 
		u.setPassword(password);
		u.setEmail(email);
		u.setName(name);
		u.setLastname(lastname);
		
		try {
			userController.addUser(u);
			return "userDetail.jsf?userId=" + u.getId() + "&faces-redirect=true";
				
		}
		catch (ConstraintViolationException a) {
			errorMessage = a.getMessage();
			return "userNotCreated.jsf";
		}
		
			
	}
	public String deleteUser(String id) {
		int userId = Integer.valueOf(id);
		userController.deleteUser(userId);
		return "allUsers?faces-redirect=true";
	}
	public List<User> getAllUsers(){
		return userController.getAllUsers();
	}


	
}
