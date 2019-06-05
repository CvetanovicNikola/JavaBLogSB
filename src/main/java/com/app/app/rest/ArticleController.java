package com.app.app.rest;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.app.app.exceptions.ArticleNotFoundException;
import com.app.app.exceptions.UserNotFoundException;
import com.app.app.model.Article;
import com.app.app.model.User;
import com.app.app.repository.ArticleRepository;

@RestController
public class ArticleController {
	
	@Autowired
	ArticleRepository articleRepository;
	@Autowired
	UserController userController;
	
	@PostMapping("/articles")
	public Article createArticle(  @PathVariable int id, @RequestBody Article article) {
		Optional<User> user = userController.getUserById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("No such user!");
		}
		article.setUser(user.get());
		articleRepository.save(article);
		return article;
	}
	
	@PutMapping("/articles/{articleId}")
	public Article updateArticle ( @PathVariable int articleId, Article article) {
		
		Optional<Article> articleFromDbOptional = articleRepository.findById(articleId);
		if(!articleFromDbOptional.isPresent()) {
			throw new ArticleNotFoundException("No such article!");
		}
		Article articleFromDb = articleFromDbOptional.get();
		articleFromDb = article;
		return articleFromDb;
		
	}
	
	@GetMapping("/articles/{articleId}")
	public Optional<Article> getArticle(@PathVariable int articleId){
		
		Optional<Article> article = articleRepository.findById(articleId);
		if(!article.isPresent()) {
			throw new ArticleNotFoundException("No such article!");
		}
		return article;
		
	}
	
	@GetMapping("/articles")
	public List<Article> getAllArticles(){
		return articleRepository.findAll();
	}
	
	@DeleteMapping("/articles/{articleId}")
	public void deleteArticle(@PathVariable int articleId) {
		Optional<Article> article = articleRepository.findById(articleId);
		if(!article.isPresent()) {
			throw new ArticleNotFoundException("No such article");
		}
		articleRepository.deleteById(articleId);
	}
	
	
}
