package com.app.app.rest;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.app.exceptions.ArticleNotFoundException;
import com.app.app.exceptions.TagNotFoundException;
import com.app.app.model.Article;
import com.app.app.model.Tag;
import com.app.app.repository.ArticleRepository;
import com.app.app.repository.TagRepository;

@RestController
public class TagController {
	
	@Autowired
	TagRepository tagRepository;
	@Autowired
	ArticleRepository articleRepository;
	
	@PostMapping("/articles/{articleId}/tags")
	public Tag addTag(@PathVariable int articleId, @Valid @RequestBody Tag tag) {
		tag.setArticle(articleRepository.findById(articleId)
					.orElseThrow(() -> new ArticleNotFoundException("No such article!")));
		tagRepository.save(tag);
		return tag;
	} 
	
	@DeleteMapping("/articles/{articleId}/tags/{tagId}")
	public void deleteTag(@PathVariable int articleId, @PathVariable int tagId) {
		tagRepository.delete(tagRepository.findById(tagId)
				.orElseThrow(() -> new TagNotFoundException("No such tag!")));
	
		}
	
	@GetMapping("/articles/{articleId}/tags")
	public List<Tag> getAllTags(@PathVariable int articleId){
		return tagRepository.findAll().stream()
				.filter(t -> t.getArticle().getId() == articleId)
				.collect(Collectors.toList());
	
	}

}
