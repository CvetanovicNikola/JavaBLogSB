package com.app.app.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "article")
@Data
@NoArgsConstructor

public class Article {
	
	@Id
	@GeneratedValue
	
	private int id;
	
	@Size(min=3, max=50)
	private String title;
	
	@Size(min=5)
	private String content;
	@JsonIgnore
	private int rating;
	
	private double averageRating;

	@JsonIgnore
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;
	private LocalDateTime createdTime;


}
