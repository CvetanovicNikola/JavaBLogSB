package com.app.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.app.model.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer>{

}
