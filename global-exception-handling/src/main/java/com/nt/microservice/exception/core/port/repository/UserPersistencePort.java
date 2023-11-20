package com.nt.microservice.exception.core.port.repository;

import java.util.List;

import com.nt.microservice.exception.adapter.persistence.entity.UserEntity;
import com.nt.microservice.exception.core.port.user.User;

public interface UserPersistencePort {
	
	List<UserEntity> getUserDetails(); 
	void saveUser(User user);
	UserEntity getUserById(Long userId);

}
