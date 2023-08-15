package com.college.service;

import java.util.List;

import com.college.entity.RegistrationRequest;

public interface RegistrationRequestService {
	void saveRegistrationRequest(RegistrationRequest registrationRequest);

	List<RegistrationRequest> getRegistrationRequests();

	RegistrationRequest getRegistrationRequestById(Long requestId);
	
}
