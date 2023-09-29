package com.service.rating.services;

import java.util.List;

import com.service.rating.entities.Rating;

public interface RatingService {

	//create
	Rating create(Rating rating);
	
	//get All Ratings
	List<Rating> getRatings();
	
	//get all by hotel
	List<Rating> getRatingsByHotelId(String hotelId);
	
	//get all by  userId
	List<Rating> getRatingsByUserId(String userId);
	
}
