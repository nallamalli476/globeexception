package com.nt.microservice.exception.service;

import java.util.List;

import com.nt.microservice.exception.adapter.persistence.entity.UserEntity;
import com.nt.microservice.exception.core.usecase.service.user.UserCommand;

public interface UserServiceUseCase {

	public List<UserEntity> getUserDetails();
	
	public void saveUser(UserCommand userCommand);
	
	UserEntity getUserById(Long id);

}
