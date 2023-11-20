package com.nt.microservice.exception.controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nt.microservice.exception.adapter.persistence.entity.UserEntity;
import com.nt.microservice.exception.core.usecase.service.user.UserCommand;
import com.nt.microservice.exception.service.UserServiceUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/v1/users", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
		MediaType.APPLICATION_JSON_VALUE })
@RequiredArgsConstructor
@Slf4j
public class UserController {

	private final UserServiceUseCase userService;

	@GetMapping("/getUsers")
	public ResponseEntity<List<UserEntity>> getUserDetails() {
		return ResponseEntity.ok(userService.getUserDetails());
	}

	@PostMapping("/save")
	public ResponseEntity saveUser(@Valid @RequestBody UserCommand userCommand) {
		log.info("New request received to user: {}", userCommand);
		userService.saveUser(userCommand);
		return ResponseEntity.accepted().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {
		return ResponseEntity.ok(userService.getUserById(id));
	}

}
