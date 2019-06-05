package com.app.app.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;


@Entity
@Data
public class Comment {
	
	@Id
	@GeneratedValue
	private long id;
	private LocalDateTime creted;
	private String text;
	private int upVote;
	private int downVote;
	@ManyToOne
	private User user;
	@ManyToOne
	private Article article;
	
	public Comment() {}

}
