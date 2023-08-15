package com.college.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.college.dto.AttendanceStudentDto;
import com.college.dto.StudentDto;
import com.college.entity.Attendance;
import com.college.entity.Student;
import com.college.service.StudentService;
import com.college.util.dto.DtoUtils;

@RestController
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private DtoUtils dtoUtils;

	@GetMapping
	public ResponseEntity<StudentDto> getStudent(Principal username) {
		String usernameString = username.getName();
		Student student = studentService.getStudentByUsername(usernameString);
		StudentDto studentDto = dtoUtils.getStudentDtofromEntity(student);
		return ResponseEntity.ok(studentDto);
	}
	
	@GetMapping("/attendance")
	public ResponseEntity<List<AttendanceStudentDto>> getAttendance(Principal principal) {
		String username = principal.getName();
		Student student = studentService.getStudentByUsername(username);
		List<Attendance> attendanceList = student.getAttendance();
		List<AttendanceStudentDto> attendanceDtoList = attendanceList.stream()
				.map(attendance -> dtoUtils.getAttendanceStudentDtoFromEntity(attendance))
				.collect(Collectors.toList());
		return ResponseEntity.ok(attendanceDtoList);
	}
}
