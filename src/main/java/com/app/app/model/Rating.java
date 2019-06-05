package com.app.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;


@Entity
@Data
public class Rating {
	@Id
	@GeneratedValue
	private long id;
	private int rating;
	@ManyToOne
	private User user;
	@ManyToOne
	private Article article;
	
	public Rating() {}
}
