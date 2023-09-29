package com.hotel.service.HotelService.controller;

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

import com.hotel.service.HotelService.daoService.HotelService;
import com.hotel.service.HotelService.entities.Hotel;

@RestController
@RequestMapping("/hotels")
public class HotelController {
	
	@Autowired
	private HotelService service;
	
	//create
	@PostMapping("/createHotel")
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
		System.out.println(hotel.getName());
		System.out.println(hotel.getLocation());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.createHotel(hotel));
	}
	
	//getAll
	@GetMapping("/allHotels")
	public ResponseEntity<List<Hotel>> getAll(){
		System.out.println("in lsting all hotels");
		return ResponseEntity.ok(service.getAll());
	}
	
	//getsingle
	@GetMapping("{hotelId}")
	public ResponseEntity<Hotel> getSingle(@PathVariable String hotelId){
		return ResponseEntity.status(HttpStatus.OK).body(service.getHotel(hotelId));  	
	}
	@GetMapping("/test")
	public String testHotel(){
		return "prachi";
	}

}
