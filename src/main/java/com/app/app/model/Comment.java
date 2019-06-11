package com.app.app.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class Comment {
	
	@Id
	@GeneratedValue
	private int id;
	private LocalDateTime creted;
	@Size(min=1, max=100, message="A comment must be between 1 and 100 characters long!")
	private String text;
	private int upVote;
	private int downVote;
	@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)
	private User voted;
//	private Set<Integer> voted;
	@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)
	private User user;
	@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)
	private Article article;
	private LocalDateTime createdTime;
//	public void addVoted(int userId) {
//		voted.add(userId);
//	}
	
}
