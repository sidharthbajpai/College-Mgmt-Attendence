package com.college.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
