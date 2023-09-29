package com.hotel.service.HotelService.daoService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hotel.service.HotelService.entities.Hotel;
import com.hotel.service.HotelService.entities.Rating;
import com.hotel.service.HotelService.exception.ResourceNotFoundException;
import com.hotel.service.HotelService.repository.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired(required = true)
	HotelRepository repo;
	
	@Autowired(required=true)
	RestTemplate restTemplate;
	
	@Override
	public Hotel createHotel(Hotel hotel) {
		// TODO Auto-generated method stub
		String hotelId=UUID.randomUUID().toString();
		hotel.setId(hotelId);
		return repo.save(hotel);
	}

	@Override
	public List<Hotel> getAll() {
		// TODO Auto-generated method stub
		List<Rating> ratings=restTemplate.getForObject("http://RATING-SERVICE/rating/getAllRatings", ArrayList.class);
		System.out.println(ratings);
		return repo.findAll();
	}

	@Override
	public Hotel getHotel(String id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElseThrow(()->new ResourceNotFoundException("hotel with given id is not found in the records,"+id));
	}

	@Override
	public void deleteHotel(Hotel hotel) {
		// TODO Auto-generated method stub
		repo.delete(hotel);

	}

}
