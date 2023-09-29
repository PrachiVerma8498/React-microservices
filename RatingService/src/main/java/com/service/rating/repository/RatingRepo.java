package com.service.rating.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.service.rating.entities.Rating;

public interface RatingRepo extends JpaRepository<Rating, String> {
	
	//custom finder methods
	List<Rating> findByUserId(String userId);
	List<Rating> findByHotelId(String hotelId);

}
