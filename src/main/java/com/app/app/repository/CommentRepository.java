package com.app.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.app.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
