package com.app.app.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.app.exceptions.CommentNotFoundException;
import com.app.app.model.Comment;
import com.app.app.repository.CommentRepository;


@RestController
public class CommentController {
	
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	UserController userController;
	@Autowired
	ArticleController articlecontroller;
	
	@PostMapping("users/{userId}/articles/{articleId}/comments")
	public Comment addComment(@PathVariable int userId, @PathVariable int articleId, @RequestBody Comment comment) {
		comment.setArticle(articlecontroller.getArticle(articleId));
		comment.setUser(userController.getUserById(userId));
		commentRepository.save(comment);
		return comment;
	}
	
	@GetMapping("articles/{articleId}/comments")
	public List<Comment> getAllComments(@PathVariable int articleId){
		return commentRepository.findAll().stream()
				.filter(c -> c.getArticle().getId() == articleId)
				.collect(Collectors.toList());
									
	}
	
	@GetMapping("comments/{commentId}")
	public Comment getCommentById(@PathVariable int commentId) {
		return commentRepository.findById(commentId)
				.orElseThrow(() -> new CommentNotFoundException("No such comment!"));
	}
	
	@DeleteMapping("comments/{commentId}")
	public void deleteComment(@PathVariable int commentId) {
		commentRepository.delete(getCommentById(commentId));
	}
}
