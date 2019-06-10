package com.app.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class Rating {
	@Id
	@GeneratedValue
	private int id;
	
	private int rating;
	@JsonIgnore
	@ManyToOne
	private User user;
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Article article;
		
}
