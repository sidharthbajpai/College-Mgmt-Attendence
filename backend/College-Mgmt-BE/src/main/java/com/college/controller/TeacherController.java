package com.college.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.college.dto.AttendanceDetailsInDto;
import com.college.dto.AttendanceDetailsOutDto;
import com.college.dto.TeacherDto;
import com.college.entity.Attendance;
import com.college.entity.Department;
import com.college.entity.Student;
import com.college.entity.Teacher;
import com.college.enums.AttendanceStatus;
import com.college.service.AttendanceService;
import com.college.service.TeacherService;
import com.college.util.dto.DtoUtils;

import lombok.Data;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private AttendanceService attendanceService;
	@Autowired
	private DtoUtils dtoUtils;

	@GetMapping
	public ResponseEntity<TeacherDto> getTeacherDetails(Principal username) {
		String usernameString = username.getName();
		Teacher teacher = teacherService.getTeacherByUsername(usernameString);
		TeacherDto teacherDto = dtoUtils.getTeacherDtofromEntity(teacher);
		return ResponseEntity.ok(teacherDto);
	}
	
	@GetMapping("/attendance")
	// TODO move logic to some separate place?
	public ResponseEntity<List<AttendanceDetailsOutDto>> getAttendancesForDate(@RequestParam() @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, Principal principal) {
		String username = principal.getName();
		Teacher teacher = teacherService.getTeacherByUsername(username);
		Long departmentId = teacher.getDepartment().getDepartmentId();
		List<Attendance> attendanceList = attendanceService.getAllAttendanceByDateAndDeptId(date, departmentId);
		List<Student> departmentStudents = teacher.getDepartment().getStudents();
		List<AttendanceDetailsOutDto> attendaceDtoList = new ArrayList<>();
		boolean isMatchFound = false;
		for(Student student : departmentStudents)  {
			for(Attendance attendance: attendanceList) {
				if(attendance.getStudent().getStudentId().equals(student.getStudentId())) {
					AttendanceDetailsOutDto attendaceDto = new AttendanceDetailsOutDto(attendance.getAttendanceId(), attendance.getStatus(), student.getStudentId(), student.getFirstName(), student.getLastName());
					attendaceDtoList.add(attendaceDto);
					attendanceList.remove(attendance);
					isMatchFound = true;
					break;
				}
			}
			if(!isMatchFound) {
				AttendanceDetailsOutDto attendaceDto = new AttendanceDetailsOutDto(null, AttendanceStatus.UNDECIDED, student.getStudentId(), student.getFirstName(), student.getLastName());
				attendaceDtoList.add(attendaceDto);
			}
		}
		return ResponseEntity.ok(attendaceDtoList);
	}
	
	//TODO make department name and role name unique in schema
	@PutMapping("/attendance")
	public ResponseEntity<?> saveAttendancesForDate(@RequestBody List<AttendanceDetailsInDto> attendancesDto, @RequestParam() @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, Principal principal) {
		String username = principal.getName();
		Department department = teacherService.getTeacherByUsername(username).getDepartment();
		Map<Boolean, List<Attendance>> isAttendanceIdPresentListMap = attendancesDto
				.stream()
				.map(a -> dtoUtils.getAttendanceFromInputDto(a, date))
				.collect(Collectors.partitioningBy(a -> a.getAttendanceId() != null));
		attendanceService.saveAll(isAttendanceIdPresentListMap.get(true));
		List<Attendance> concernedAttendances = isAttendanceIdPresentListMap.get(false);
		HashMap<StudentIdDate, Attendance> concernedAttendancesMap = concernedAttendances
				.stream()
				.collect(Collectors.toMap(
						a -> new StudentIdDate(a.getStudent().getStudentId(), a.getAttendanceDate()),
						a -> a,
						(prev, next) -> next,
						HashMap::new));
		List<Student> concernedStudents = concernedAttendances
				.stream()
				.map(a -> a.getStudent())
				.collect(Collectors.toList());
		//TODO right now making this map consisting of date is not important as we receive date argument from request as well as we use it to get same date values from table, so we don't really have to check if both date and student id are different as dates will always be same. but in future if we add functionality to receive attendances for many dates then this functionality can be useful. In future migrate this logic to somwhere else?
		//TODO add exceptions and exception handling
		List<Attendance> concernedLoadedAttendances = attendanceService.getConcernedAttenences(date, department, concernedStudents);
		concernedLoadedAttendances.forEach(a -> {
			StudentIdDate key = new StudentIdDate(a.getStudent().getStudentId(), a.getAttendanceDate());
			Attendance updatedAttendance = concernedAttendancesMap.get(key);
			updatedAttendance.setAttendanceId(a.getAttendanceId());
			concernedAttendancesMap.put(key, updatedAttendance);
		});
		attendanceService.saveAll(concernedAttendancesMap.values());
		return ResponseEntity.ok().build();
	}
	
	@Data
	class StudentIdDate {
		private final Long studentId;
		private final LocalDate date;
	}
	
	//TODO Arrange todos at right places
	//TODO if adding functionality to admin to add department and role, make exception to not allow same name roles and departments
	//TODO Added username in both student, teacher and admin table as well as their foreign 1 - 1 relationship user table. Probably should also have to change the name of user table  to user_details table.

}
