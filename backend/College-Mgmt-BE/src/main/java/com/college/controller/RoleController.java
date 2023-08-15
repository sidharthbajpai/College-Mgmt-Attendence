package com.college.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.college.dto.RoleOutDto;
import com.college.entity.Role;
import com.college.service.RoleService;
import com.college.util.dto.DtoUtils;

@RestController
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private DtoUtils dtoUtils;

	@GetMapping
	public ResponseEntity<List<RoleOutDto>> getAllRoles() {
		List<Role> roles = roleService.getAllRoles();
		List<RoleOutDto> rolesDto = roles
				.stream()
				.map(role -> dtoUtils.getDtoFromRole(role))
				.collect(Collectors.toList());
		return ResponseEntity.ok(rolesDto);
	}
}
