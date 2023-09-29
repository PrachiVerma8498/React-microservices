package com.user.service.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.user.service.entities.User;
import com.user.service.service.UserService;
import com.user.service.service.UserServiceImpl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/userService")
public class UserController {

	@Autowired
	private UserService service;
	
	@Autowired
	private RestTemplate restTemplate;
	private static Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);
	
	
	//create
	@PostMapping("/saveUser")
	public ResponseEntity<User> createUser(@RequestBody User user){
		System.out.println("inside save user method");
		User user1=service.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
	
	//single user get
	@GetMapping("/{userId}")
	//@CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
	//@Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
	@RateLimiter(name="userRateLimiter" , fallbackMethod = "ratingHotelFallBack")
	public ResponseEntity<User> getSingleUser(@PathVariable("userId") String id){
		logger.info("calling getSingleUser method");
		logger.info("retry count : {}"+retryCount++);
		User user=service.getUser(id);
		return ResponseEntity.ok(user);
	}
	
	int retryCount=1;
		//creating circuit breaker fallback method
		public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
			logger.info("fallback method is executed because some service is down ::"+ex.getMessage());
			
			  User user =
			  User.builder().email("dummy@gmail.com").name("dummy").about("dumb").build(); 
			  return new ResponseEntity<>(user,HttpStatus.BAD_REQUEST );
			 
			/*
			 * User user = User.builder().email("dummy@gmail.com").name("Dummy").
			 * about("This user is created dummy because some service is down").userId(
			 * "141234").build(); return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
			 */
		}
		
		/*
		 * Object forObject = restTemplate.getForObject(
		 * "http://localhost:6534/users/3e7bc6e0-f7ac-48ca-ab91-9bfb0b51fee3",
		 * Arrays.class);
		 */
		
		
		
	
	
	//get all users
	@GetMapping(value="/users",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> getAllUser(){
		List<User> users= service.getAllUser();
		
		return ResponseEntity.ok(users);
		
	}
	
}
