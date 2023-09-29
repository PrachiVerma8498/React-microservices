package com.user.service.service;

import java.util.List;

import com.user.service.entities.User;

public interface UserService {
	
	//user operations
	
	//create
	User saveUser(User user);
	
	//get all user
	List<User> getAllUser();
	
	//get single User
	User getUser(String userId);
	
	void deleteUser(String userId);
	
	User updateUser(String userId);
	

}
