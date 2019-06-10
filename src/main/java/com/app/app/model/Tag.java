package com.app.app.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "tag")
@NoArgsConstructor
@Data
public class Tag {
	
	@Id
	@GeneratedValue
	private int id;
	private String value;
	@JsonIgnore
	@ManyToOne(fetch= FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="article_id")
	private Article article;
		

	public Tag(int id, String value) {
		super();
		this.id = id;
		this.value = value;
	}
	
}
