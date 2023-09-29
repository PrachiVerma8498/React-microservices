package com.hotel.service.HotelService.daoService;

import java.util.List;

import com.hotel.service.HotelService.entities.Hotel;

public interface HotelService {

	//create
	Hotel createHotel(Hotel hotel);
	
	//getAll
	List<Hotel> getAll();
	
	//getSingle
	Hotel getHotel(String id);
	
	//delete
	void deleteHotel(Hotel hotel);
	
}
