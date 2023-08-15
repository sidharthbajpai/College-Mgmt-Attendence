package com.college.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	
	Optional<Student> findFirstByUsername(String username);
	
}
