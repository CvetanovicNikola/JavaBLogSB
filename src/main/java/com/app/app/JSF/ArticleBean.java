package com.app.app.JSF;

import java.time.LocalDateTime;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.app.exceptions.UserNotFoundException;
import com.app.app.model.Article;
import com.app.app.rest.ArticleController;
import lombok.Data;


@Named("article")
@SessionScoped
@Data
public class ArticleBean {
	
	@Inject
	UserBean userBean;
	@Autowired
	ArticleController articleController;
	
	private String title;
	private String content;
	private LocalDateTime created;
	private String message;
	private Article article;
	private String articleId;
	
	
	public Article getArticleById() {
		int id = Integer.valueOf(articleId);
		return articleController.getArticle(id).get();
				
	}
	
	public String createArticle() {
		Article a = new Article();
		a.setUser(userBean.getUser());
		//System.out.println(userBean.getLoggedUser().getName());
		a.setTitle(title);
		a.setContent(content);
		//a.setCreated(created);
		
		try {
			articleController.createArticle(userBean.getLoggedUser().getId(), a);
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
	
}
