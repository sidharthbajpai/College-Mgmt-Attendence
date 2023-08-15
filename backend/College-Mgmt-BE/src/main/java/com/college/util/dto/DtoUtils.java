package com.college.util.dto;

import java.time.LocalDate;

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

public interface DtoUtils {
	RegistrationRequest getRegistrationRequestFromDto(RegistrationRequestInDto registrationRequestDto);

	StudentDto getStudentDtofromEntity(Student student);

	AttendanceStudentDto getAttendanceStudentDtoFromEntity(Attendance attendance);

	TeacherDto getTeacherDtofromEntity(Teacher teacher);

	RegistrationRequestOutDto getRegistrationRequestOutDtoFromEntity(RegistrationRequest registrationRequest);
	
	Attendance getAttendanceFromInputDto(AttendanceDetailsInDto attendanceDto, LocalDate attendanceDate);

	AdminDto getAdminDtofromEntity(Admin admin);

	DepartmentOutDto getDtoFromDepartment(Department department);

	RoleOutDto getDtoFromRole(Role role);
}
