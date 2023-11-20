package com.nt.microservice.exception.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.nt.microservice.exception.adapter.persistence.entity.UserEntity;
import com.nt.microservice.exception.core.port.repository.UserPersistencePort;
import com.nt.microservice.exception.core.port.user.User;
import com.nt.microservice.exception.core.usecase.service.user.UserCommand;
import com.nt.microservice.exception.service.UserServiceUseCase;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServiceUseCase {

	private final UserPersistencePort userPersistencePort;

	@Override
	public List<UserEntity> getUserDetails() {

		return userPersistencePort.getUserDetails();
	}

	@Override
	public void saveUser(UserCommand userCommand) {
		User user = User.builder().firstName(userCommand.getFirstName()).lastName(userCommand.getLastName())
				.userId(userCommand.getUserId()).build();
		userPersistencePort.saveUser(user);

	}

	@Override
	public UserEntity getUserById(Long id) {
		return userPersistencePort.getUserById(id);
	}

}
