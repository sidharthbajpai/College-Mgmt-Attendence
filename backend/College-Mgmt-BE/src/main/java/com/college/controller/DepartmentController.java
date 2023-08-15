package com.college.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.college.dto.DepartmentOutDto;
import com.college.entity.Department;
import com.college.service.DepartmentService;
import com.college.util.dto.DtoUtils;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private DtoUtils dtoUtils;

	@GetMapping
	public ResponseEntity<List<DepartmentOutDto>> getAllDepartments() {
		List<Department> departments = departmentService.getAllDepartments();
		List<DepartmentOutDto> departmentsDto = departments
				.stream()
				.map(department -> dtoUtils.getDtoFromDepartment(department))
				.collect(Collectors.toList());
		return ResponseEntity.ok(departmentsDto);
	}
}
