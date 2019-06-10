package com.app.app.rest;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.app.app.exceptions.ArticleNotFoundException;
import com.app.app.model.Article;
import com.app.app.repository.ArticleRepository;

@RestController
public class ArticleController {
	
	@Autowired
	ArticleRepository articleRepository;
	@Autowired
	UserController userController;
	
	@PostMapping("/users/{userId}/articles")
	public Article createArticle(  @PathVariable int userId, @Valid @RequestBody Article article) {
		article.setUser(userController.getUserById(userId));
		articleRepository.save(article);
		return article;
	}
	
	@PutMapping("/articles/{articleId}")
	public Article updateArticle ( @PathVariable int articleId, @RequestBody Article article) {
		return articleRepository.findById(articleId)
						.map(a -> {
							a.setContent(article.getContent());
							return articleRepository.save(a);
						}).orElseThrow(() -> new ArticleNotFoundException("No such article!"));
			
	}
	
	@GetMapping("/articles/{articleId}")
	public Article getArticle(@PathVariable int articleId){
		return articleRepository.findById(articleId)
					.orElseThrow(() -> new ArticleNotFoundException("No such article!"));
				
	}
	
	@GetMapping("/articles")
	public List<Article> getAllArticles(){
		return articleRepository.findAll();
	}
	
	@DeleteMapping("/articles/{articleId}")
	public void deleteArticle(@PathVariable int articleId) {
		articleRepository.delete(articleRepository.findById(articleId)
				.orElseThrow(() -> new ArticleNotFoundException("No such article!")));
		
	}
	
	public Article averageRating(int articleId, double averageRating) {
		Article article = getArticle(articleId);
		article.setAverageRating(averageRating);
		articleRepository.save(article);
		return article;
	}
	
	
}
