package com.college.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import com.college.entity.Attendance;
import com.college.entity.Department;
import com.college.entity.Student;

public interface AttendanceService {
	
	List<Attendance> getAllAttendanceByDateAndDeptId(LocalDate date, Long departmentId);

	List<Attendance> getConcernedAttenences(LocalDate date, Department department, List<Student> concernedStudents);

	void saveAll(Collection<Attendance> collection);

}
