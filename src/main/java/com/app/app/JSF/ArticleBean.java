package com.app.app.JSF;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.app.exceptions.UserNotFoundException;
import com.app.app.model.Article;
import com.app.app.model.Tag;
import com.app.app.rest.ArticleController;
import com.app.app.rest.TagController;

import lombok.Data;


@Named("article")
@SessionScoped
@Data
public class ArticleBean {
	
	@Inject
	UserBean userBean;
	@Inject
	TagBean tagBean;
	@Autowired
	ArticleController articleController;
	@Autowired
	TagController tagController;
	
	
	private String title;
	private String content;
	private LocalDateTime created;
	private String message;
	private Article article;
	private String articleId;
	private Set<Tag> articleTags;
	private String tagValue;
	
	
	
	public Article getArticleById() {
		int id = Integer.valueOf(articleId);
		return articleController.getArticle(id);
				
	}
	
	public String createArticle() {
		articleTags = new HashSet<>();
		Article a = new Article();
		a.setUser(userBean.getUser());
		//System.out.println(userBean.getLoggedUser().getName());
		a.setTitle(title); 
		a.setContent(content);
				
		
		//a.setCreated();
		
		try {
			articleController.createArticle(userBean.getLoggedUser().getId(), a);
			Tag tag = new Tag();
			tag.setValue(tagValue);
			tagController.addTag(a.getId(), tag);

			return "articleDetail.jsf?articleId=" + a.getId() + "&faces-redirect=true";
		}
		catch(UserNotFoundException e){
			message = e.getMessage();
			System.out.println(message);
			return "articleNotCreated?faces-redirect=true";
		}
	}
	
	public String deleteArticle(String articleId) {
		int artId = Integer.valueOf(articleId);
		articleController.deleteArticle(artId);
		return "allArticles?faces-redirect=true";
	}
	
	public List<Article> getAllArticles(){
		return articleController.getAllArticles();
	}
	public void createTag() {
		Tag tag = new Tag();
		tag.setValue("cale");
		articleTags.add(tag);
	}
	
}
