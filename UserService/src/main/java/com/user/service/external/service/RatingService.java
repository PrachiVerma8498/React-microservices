package com.user.service.external.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.user.service.entities.Rating;

@FeignClient(name="RATING-SERVICE")
public interface RatingService {
	
	//get
	
	//POST
	@PostMapping("/createRating")
	public Rating createRating(Rating values);
	
	//put
	@PutMapping("/rating/{ratingId}")
	public Rating updateRating(Rating rating, @PathVariable int ratingId);
	//

}
