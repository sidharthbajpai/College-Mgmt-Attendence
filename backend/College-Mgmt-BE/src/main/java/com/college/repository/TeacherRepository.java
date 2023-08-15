package com.college.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

	Optional<Teacher> findFirstByUsername(String username);

}
