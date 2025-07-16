package com.store.security.store_security.controller;

import com.store.security.store_security.dto.AllUserDto;
import com.store.security.store_security.dto.UserDto;
import com.store.security.store_security.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

	private final IUserService userService;


	@PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> userDetails(@PathVariable("id") Long username)
	{
		return ResponseEntity.status(HttpStatus.OK).body(userService.findUser(username));
	}

	@GetMapping
	public ResponseEntity<AllUserDto> allUser()
	{
		return ResponseEntity.status(HttpStatus.OK).body(userService.allUser());
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id,@RequestBody UserDto userDto)
	{
		return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id,userDto));
	}



}
