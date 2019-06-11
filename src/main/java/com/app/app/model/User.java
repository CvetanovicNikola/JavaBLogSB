package com.app.app.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue
	private int id;
	@NotNull
	@Size(min=3, max=30, message="Name must be between 3 and 30 characters long.")
	private String name;
	@NotNull
	@Size(min=3, max=30, message="Lastame must be between 3 and 30 characters long.")
	private String lastname;
	@NotNull
	@Size(min=5, max=20, message="Username must be between 5 and 20 characters long.")
	private String username;
	@NotNull
	@Size(min=8, max=20, message="Password must be between 8 and 20 characters long.")
	private String password;
	@NotNull
	@Email(message="Enter a valid email.")
	private String email;
	private LocalDateTime createdTime;
	


}
