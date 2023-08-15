package com.college.util.dto.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.college.dto.AdminDto;
import com.college.dto.AttendanceDetailsInDto;
import com.college.dto.AttendanceStudentDto;
import com.college.dto.DepartmentOutDto;
import com.college.dto.RegistrationRequestInDto;
import com.college.dto.RegistrationRequestOutDto;
import com.college.dto.RoleOutDto;
import com.college.dto.StudentDto;
import com.college.dto.TeacherDto;
import com.college.entity.Admin;
import com.college.entity.Attendance;
import com.college.entity.Department;
import com.college.entity.RegistrationRequest;
import com.college.entity.Role;
import com.college.entity.Student;
import com.college.entity.Teacher;
import com.college.enums.RequestStatus;
import com.college.repository.DepartmentRepository;
import com.college.repository.RoleRepository;
import com.college.repository.StudentRepository;
import com.college.util.dto.DtoUtils;

@Component
public class DtoUtilsImpl implements DtoUtils {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private StudentRepository studentRepository;
	
	public RegistrationRequest getRegistrationRequestFromDto(RegistrationRequestInDto registrationRequestDto) {
		Department department = null;
		if(registrationRequestDto.getDepartmentId()!=null) {
			//TODO maybe create a separate registration table for admin or maybe all
			department = departmentRepository.getReferenceById(registrationRequestDto.getDepartmentId());
		}
		return new RegistrationRequest(
				null,
				registrationRequestDto.getFirstName(),
				registrationRequestDto.getLastName(),
				registrationRequestDto.getUsername(),
				registrationRequestDto.getPassword(),
				registrationRequestDto.getPhoneNumber(),
				registrationRequestDto.getLocation(),
				registrationRequestDto.getEmail(),
				RequestStatus.UNKNOWN,
				department,
				roleRepository.getReferenceById(registrationRequestDto.getRoleId()));
	}

	@Override
	public StudentDto getStudentDtofromEntity(Student student) {
		return new StudentDto(
				student.getStudentId(), 
				student.getFirstName(), 
				student.getLastName(), 
				student.getUsername(), 
				student.getPhoneNumber(), 
				student.getLocation(), 
				student.getEmail(), 
				student.getDepartment().getDepartmentName());
	}
	
	@Override
	public TeacherDto getTeacherDtofromEntity(Teacher teacher) {
		return new TeacherDto(
				teacher.getTeacherId(), 
				teacher.getFirstName(), 
				teacher.getLastName(), 
				teacher.getUsername(), 
				teacher.getPhoneNumber(), 
				teacher.getLocation(), 
				teacher.getEmail(), 
				teacher.getDepartment().getDepartmentName());
	}

	@Override
	public AttendanceStudentDto getAttendanceStudentDtoFromEntity(Attendance attendance) {
		return new AttendanceStudentDto(
				attendance.getAttendanceDate(),
				attendance.getStatus());
	}

	@Override
	public RegistrationRequestOutDto getRegistrationRequestOutDtoFromEntity(RegistrationRequest registrationRequest) {
		Long departmentId = null;
		String departmentName = null;
		Department department = registrationRequest.getDepartment();
		if(department != null) {
			departmentId = department.getDepartmentId();
			departmentName = department.getDepartmentName();
		}
		return new RegistrationRequestOutDto(
				registrationRequest.getRequestId(),
				registrationRequest.getFirstName(),
				registrationRequest.getLastName(),
				registrationRequest.getUsername(),
				registrationRequest.getPhoneNumber(),
				registrationRequest.getLocation(),
				registrationRequest.getEmail(),
				registrationRequest.getStatus(),
				departmentId,
				departmentName,
				registrationRequest.getRole().getRoleId(),
				registrationRequest.getRole().getRoleName().toString().split("_")[1]);
	}

	@Override
	public Attendance getAttendanceFromInputDto(AttendanceDetailsInDto attendanceDto, LocalDate attendanceDate) {
		return new Attendance(
				attendanceDto.getAttendanceId(),
				attendanceDate,
				attendanceDto.getStatus(),
				studentRepository.getReferenceById(attendanceDto.getStudentId()));
	}

	@Override
	public AdminDto getAdminDtofromEntity(Admin admin) {
		return new AdminDto(
				admin.getAdminId(), 
				admin.getFirstName(), 
				admin.getLastName(), 
				admin.getUsername(), 
				admin.getPhoneNumber(), 
				admin.getLocation(), 
				admin.getEmail());
	}

	@Override
	public DepartmentOutDto getDtoFromDepartment(Department department) {
		return new DepartmentOutDto(department.getDepartmentId(), department.getDepartmentName());
	}

	@Override
	public RoleOutDto getDtoFromRole(Role role) {
		return new RoleOutDto(role.getRoleId(), role.getRoleName().toString().split("_")[1]);
	}
	
}
