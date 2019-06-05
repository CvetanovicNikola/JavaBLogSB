package com.app.app.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Article {
	
	@Id
	@GeneratedValue
	private int id; 
	private String title;
	private String content;
	private int rating;
	private double averageRating;
	@ManyToOne
	private User user;
	@ManyToMany(mappedBy="articles")
	private Set<Tag> tags;
	@OneToMany(mappedBy="article")
	private Set<Comment> comments;
	@OneToMany(mappedBy="article")
	private Set<Rating> ratings;
	
	public Article() {}
	
}
