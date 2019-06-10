package com.app.app.JSF;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;

import com.app.app.model.Article;
import com.app.app.model.Tag;
import com.app.app.repository.TagRepository;
import com.app.app.rest.ArticleController;
import com.app.app.rest.TagController;
import lombok.Data;

@Named("tag")
@SessionScoped
@Data
public class TagBean {
	
	@Autowired
	private TagController tagController;
	@Autowired
	TagRepository tagRepository;
	@Autowired
	ArticleController articleController;
	
	private String tagValue;
	
	public Tag addTag(int articleId, Tag tag) {
		tagController.addTag(articleId, tag);
		return tag;
		
	}
	public Tag createTag(Tag tag) {
		tagRepository.save(tag);
		return tag;
	}
}
