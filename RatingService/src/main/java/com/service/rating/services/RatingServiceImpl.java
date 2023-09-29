package com.service.rating.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.rating.entities.Rating;
import com.service.rating.repository.RatingRepo;

@Service
public class RatingServiceImpl implements RatingService {
	
	@Autowired
	private RatingRepo repo;

	@Override
	public Rating create(Rating rating) {
		// TODO Auto-generated method stub
		return repo.save(rating);
	}

	@Override
	public List<Rating> getRatings() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public List<Rating> getRatingsByHotelId(String hotelId) {
		// TODO Auto-generated method stub
		return repo.findByHotelId(hotelId);
	}

	@Override
	public List<Rating> getRatingsByUserId(String userId) {
		// TODO Auto-generated method stub
		return repo.findByUserId(userId);
	}

}
