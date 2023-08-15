package com.college.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.entity.RegistrationRequest;

public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest, Long> {

}
