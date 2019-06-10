package com.app.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.app.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer>{
		
		
}
