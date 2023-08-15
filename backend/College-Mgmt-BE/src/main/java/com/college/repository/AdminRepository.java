package com.college.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{

	Optional<Admin> findAdminByUsername(String usernameString);

}
