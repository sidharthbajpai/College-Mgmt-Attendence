package com.college.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.college.dto.AdminDto;
import com.college.dto.RegistrationRequestOutDto;
import com.college.entity.Admin;
import com.college.entity.RegistrationRequest;
import com.college.entity.Role;
import com.college.entity.Student;
import com.college.entity.Teacher;
import com.college.entity.User;
import com.college.enums.RequestStatus;
import com.college.service.AdminService;
import com.college.service.RegistrationRequestService;
import com.college.service.StudentService;
import com.college.service.TeacherService;
import com.college.service.UserService;
import com.college.util.dto.DtoUtils;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private RegistrationRequestService registrationRequestService;
	@Autowired
	private UserService userService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private DtoUtils dtoUtils;
	
	//TODO make admin details method
	@GetMapping
	public ResponseEntity<?> getAdminDetails(Principal username) {
		String usernameString = username.getName();
		Admin admin = adminService.getAdminByUsername(usernameString);
		AdminDto adminDto = dtoUtils.getAdminDtofromEntity(admin);
		return ResponseEntity.ok(adminDto);
	}
	
	@GetMapping("/requests")
	public ResponseEntity<List<RegistrationRequestOutDto>> getRegistrationRequests() {
		List<RegistrationRequest> registrationRequests = registrationRequestService.getRegistrationRequests();
		List<RegistrationRequestOutDto> requestOutDtoList = registrationRequests
				.stream()
				.map(r -> dtoUtils.getRegistrationRequestOutDtoFromEntity(r))
				.collect(Collectors.toList());
		return ResponseEntity.ok(requestOutDtoList);
	}
	
	@PutMapping("/requests/evaluate")
	//TODO when receiving request without departmentId its still doing wrong, fix, maybe create seperate end points
	public ResponseEntity<?> updateRegistrationRequest(@RequestParam Long requestId, @RequestParam boolean acceptRequest) {
		RegistrationRequest request = registrationRequestService.getRegistrationRequestById(requestId);
		if(acceptRequest) {
			Role role = request.getRole();
			User user = new User(null, request.getUsername(), request.getPassword(), role);
			userService.saveUser(user);
			switch(role.getRoleName()) {
			case ROLE_ADMIN:
				saveAdmin(request);
				break;
			case ROLE_TEACHER:
				saveTeacher(request);
				break;
			case ROLE_STUDENT:
				saveStudent(request);
				break;
			}
			request.setStatus(RequestStatus.ACCEPTED);
		} else {
			request.setStatus(RequestStatus.DENIED);
		}
		registrationRequestService.saveRegistrationRequest(request);
		return ResponseEntity.ok().build();
	}
	
	private void saveAdmin(RegistrationRequest request) {
		Admin admin = new Admin(
				null,
				request.getFirstName(),
				request.getLastName(),
				request.getUsername(),
				request.getPhoneNumber(),
				request.getLocation(),
				request.getEmail());
		adminService.saveAdmin(admin);
	}
	
	private void saveTeacher(RegistrationRequest request) {
		Teacher teacher = new Teacher(
				null,
				request.getFirstName(),
				request.getLastName(),
				request.getUsername(),
				request.getPhoneNumber(),
				request.getLocation(),
				request.getEmail(),
				request.getDepartment());
		teacherService.saveTeacher(teacher);
	}
	
	private void saveStudent(RegistrationRequest request) {
		Student student = new Student(
				null,
				request.getFirstName(),
				request.getLastName(),
				request.getUsername(),
				request.getPhoneNumber(),
				request.getLocation(),
				request.getEmail(),
				request.getDepartment(),
				null);
		studentService.saveStudent(student);
	}
}
