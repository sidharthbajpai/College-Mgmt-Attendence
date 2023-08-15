package com.college.service.impl;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.entity.Attendance;
import com.college.entity.Department;
import com.college.entity.Student;
import com.college.repository.AttendanceRepository;
import com.college.service.AttendanceService;

@Service
// TODO change List everywhere to Collection
public class AttendanceServiceImpl implements AttendanceService {
	@Autowired
	private AttendanceRepository attendanceRepository;

	@Override
	public List<Attendance> getAllAttendanceByDateAndDeptId(LocalDate date, Long departmentId) {
		return attendanceRepository.findByDateAndDeptId(date, departmentId);
	}

	@Override
	public List<Attendance> getConcernedAttenences(LocalDate date, Department department, List<Student> concernedStudents) {
		return attendanceRepository.findByDateDeptAndStudents(date, department, concernedStudents);
	}

	@Override
	public void saveAll(Collection<Attendance> attendances) {
		attendanceRepository.saveAll(attendances);
	}

}
