package com.user.service.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.service.entities.Hotel;
import com.user.service.entities.Rating;
import com.user.service.entities.User;
import com.user.service.exception.ResourceNotFoundException;
import com.user.service.external.service.HotelService;
import com.user.service.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private HotelService hotelService;
	
	public UserServiceImpl(RestTemplateBuilder builder ) {
		super();
		this.restTemplate=builder.build();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
		messageConverters.add(converter);
		this.restTemplate.setMessageConverters(messageConverters);
		System.out.println("restTemlate object:::::"+restTemplate);
	}

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		String userId = UUID.randomUUID().toString();
		user.setUserId(userId);
		User savedUser=userRepo.save(user);
		return savedUser;
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		List<User> allUsers = userRepo.findAll();
		
		for(User user:allUsers) {
			Rating[] userRating = restTemplate.getForObject("http://RATING-SERVICE/rating/users/"+user.getUserId(), Rating[].class);
			List<Rating> ratingList = Arrays.stream(userRating).toList();
			List<Rating> hotelRatingList = ratingList.stream().map(rating->{
				ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
				Hotel hotel = forEntity.getBody();
				rating.setHotel(hotel);
				return rating;
			}).collect(Collectors.toList());
			
			user.setRating(hotelRatingList);
		}
		return allUsers;
	}

	@Override
	public User getUser(String userId) {
		// TODO Auto-generated method stub
		User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user with given id is not found in the records:"+userId));
		//fetching rating of above user from RATING SERVICE
		//Rating[] ratingOfUser=restTemplate.getForObject("http://localhost:6534/users/"+user.getUserId(), Rating[].class);
		Rating[] ratingOfUser=restTemplate.getForObject("http://RATING-SERVICE/rating/users/"+user.getUserId(), Rating[].class);
		logger.info("{}" + ratingOfUser);
		List<Rating> ratings= Arrays.stream(ratingOfUser).toList();
		 List<Rating> ratingList=ratings.stream().map(rating->{
			//call the hotel service api to get the hotel
				//ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://localhost:7665/hotel/"+rating.getHotelId(), Hotel.class);
				//ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/"+rating.getHotelId(), Hotel.class);
					System.out.println("caling hotel service using feign client");
			 Hotel hotel = hotelService.getHotel(rating.getHotelId());//forEntity.getBody();
			//set the hotel to rating
			rating.setHotel(hotel);
			//rturn the rating
			return rating;
		}).collect(Collectors.toList());
		
		user.setRating(ratingList);
		return user;
	}

	@Override
	public void deleteUser(String userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User updateUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
