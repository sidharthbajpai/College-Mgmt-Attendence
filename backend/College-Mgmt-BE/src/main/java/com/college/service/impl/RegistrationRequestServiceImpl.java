package com.college.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.entity.RegistrationRequest;
import com.college.repository.RegistrationRequestRepository;
import com.college.service.RegistrationRequestService;

@Service
public class RegistrationRequestServiceImpl implements RegistrationRequestService {
	@Autowired
	private RegistrationRequestRepository registrationRequestRepository;

	@Override
	public void saveRegistrationRequest(RegistrationRequest registrationRequest) {
		registrationRequestRepository.save(registrationRequest);
	}

	@Override
	public List<RegistrationRequest> getRegistrationRequests() {
		return registrationRequestRepository.findAll();
	}

	@Override
	public RegistrationRequest getRegistrationRequestById(Long requestId) {
		return registrationRequestRepository.findById(requestId).get();
	}
}
