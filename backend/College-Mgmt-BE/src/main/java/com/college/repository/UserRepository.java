package com.college.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findFirstByUsername(String username);

}
