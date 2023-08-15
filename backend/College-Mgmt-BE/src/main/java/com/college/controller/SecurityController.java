package com.college.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.college.dto.RegistrationRequestInDto;
import com.college.entity.RegistrationRequest;
import com.college.service.RegistrationRequestService;
import com.college.util.dto.DtoUtils;

@RestController
public class SecurityController {
	@Autowired
	private RegistrationRequestService registrationRequestService;
	@Autowired
	private DtoUtils dtoUtils;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody RegistrationRequestInDto registrationRequestInDto) {
		//TODO Add username not unique exception
		RegistrationRequest registrationRequest = dtoUtils.getRegistrationRequestFromDto(registrationRequestInDto);
		registrationRequestService.saveRegistrationRequest(registrationRequest);
		return ResponseEntity.ok().build();
	}
	
}
