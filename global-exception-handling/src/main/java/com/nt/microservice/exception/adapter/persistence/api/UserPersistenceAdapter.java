package com.nt.microservice.exception.adapter.persistence.api;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.nt.microservice.exception.adapter.persistence.entity.UserEntity;
import com.nt.microservice.exception.adapter.persistence.repository.UserReposistory;
import com.nt.microservice.exception.core.port.repository.UserPersistencePort;
import com.nt.microservice.exception.core.port.user.User;
import com.nt.microservice.exception.core.usecase.service.exception.UserDoesNotException;
import com.nt.microservice.exception.core.usecase.service.exception.UserPersistenceException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Ravi kumar
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class UserPersistenceAdapter implements UserPersistencePort {

	private final UserReposistory userReposistory;

	// private final UserMapper mapper;

	@Override
	public List<UserEntity> getUserDetails() {

		return userReposistory.findAll();
	}

	@Override
	public void saveUser(User user) {

		if (Objects.isNull(user)) {
			throw new IllegalArgumentException("user must not be null");
		}

		// UserEntity userEntity = mapper.toEntity(user);

		UserEntity userEntity = new UserEntity();
		userEntity.setFirstName(user.getFirstName());
		userEntity.setLastName(user.getLastName());
		userEntity.setUserId(user.getUserId());

		try {
			userReposistory.save(userEntity);
		} catch (DataAccessException | PersistenceException ex) {
			log.error("{}", ex);
			throw new UserPersistenceException(ex);
		}

	}

	@Override
	public UserEntity getUserById(Long userId) {
		UserEntity userEntity = null;
		try {
			Optional<UserEntity> optionalEntity = userReposistory.findById(userId);
			userEntity = optionalEntity.get();
		} catch (NoSuchElementException e) {
			throw new UserDoesNotException(userId);
		}
		return userEntity;
	}

}
