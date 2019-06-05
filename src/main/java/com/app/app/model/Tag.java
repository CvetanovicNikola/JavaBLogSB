package com.app.app.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;


@Entity
@Data
public class Tag {
	
	@Id
	@GeneratedValue
	private long id;
	private String value;
	@ManyToMany
	private Set<Article>articles;
	
	public Tag() {}
}
