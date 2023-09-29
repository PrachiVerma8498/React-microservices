package com.service.rating.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.rating.entities.Rating;
import com.service.rating.services.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingController {
	
	@Autowired
	private RatingService service;
	
	@PostMapping("/createRating")
	public ResponseEntity<Rating> create(@RequestBody Rating rating){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(rating));
	}
	
	@GetMapping("/getAllRatings")
	public ResponseEntity<List<Rating>> getRatings(){
		return ResponseEntity.ok(service.getRatings());
	}	
	
	@GetMapping("/hotel/{hotelId}")	
	public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId){
		return ResponseEntity.ok(service.getRatingsByHotelId(hotelId));
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId){
		return ResponseEntity.ok(service.getRatingsByUserId(userId));
	}
	
	@GetMapping("/test")
	public String testRate() {
		return "prachi";
	}
}
