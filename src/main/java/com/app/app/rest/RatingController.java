package com.app.app.rest;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.app.app.exceptions.ArticleAlreadyVoted;
import com.app.app.exceptions.RatingException;
import com.app.app.model.Rating;
import com.app.app.repository.ArticleRepository;
import com.app.app.repository.RatingRepository;

@RestController
public class RatingController {
		
	@Autowired
	RatingRepository ratingRepository;
	@Autowired
	UserController userController;
	@Autowired
	ArticleController articleController;
	@Autowired
	ArticleRepository articleRepostiory;
	
	
	@GetMapping("users/{userId}/rating")
	public List<Rating> getRatingsByUserId(@PathVariable int userId) {
		return ratingRepository.findAll().stream()
						.filter(r -> r.getUser().getId() == userId)
						.collect(Collectors.toList());
							
	}
	
	@GetMapping("articles/{articleId}/rating")
	public List<Rating> getRatingsByArticleId(@PathVariable int articleId){
		return ratingRepository.findAll().stream()
							.filter(r -> r.getArticle().getId() == articleId)
							.collect(Collectors.toList());
	}
	
	@PostMapping("users/{userId}/articles/{articleId}/rating")
	public Rating addRating(@PathVariable int userId, @PathVariable int articleId, @RequestBody Rating rating) {
		if(rating.getRating() <1 || rating.getRating() > 5) throw new RatingException("Rating must be between 1 and 5!");
//		getRatingsByArticleId(articleId).stream()
//						.filter(r -> r.getUser().getId() == userId)
//						.findAny()
//						.map(r -> {
//							r.setUser(userController.getUserById(userId));
//							r.setArticle(articleController.getArticle(articleId));
//							return ratingRepository.save(rating);
//						})
//						.ifPresent(r -> { throw new ArticleAlreadyVoted("You already voted for that article!");
//						});
			
		getRatingsByArticleId(articleId).stream()
						.filter(r -> r.getUser().getId() == userId)
						.findAny()
						.ifPresent(r -> { throw new ArticleAlreadyVoted("You already voted for that article!");
						});
						
		rating.setUser(userController.getUserById(userId));
		rating.setArticle(articleController.getArticle(articleId));
		ratingRepository.save(rating);
		getAverageRating(articleId);
		return rating;
	}
	
	@GetMapping("article/{articleId}/averageRating")
	public void getAverageRating (@PathVariable int articleId) {
		
		double averageRating = getRatingsByArticleId(articleId).stream()
						.mapToDouble(r -> r.getRating())
						.average()
						.orElse(Double.NaN);
		
		articleController.averageRating(articleId, averageRating);
													
	}
	
	
}
