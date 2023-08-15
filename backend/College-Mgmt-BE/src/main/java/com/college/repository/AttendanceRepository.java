package com.college.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.college.entity.Attendance;
import com.college.entity.Department;
import com.college.entity.Student;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

	@Query("SELECT a FROM Attendance a INNER JOIN a.student s WHERE a.attendanceDate = :date AND s.department.departmentId = :departmentId")
	List<Attendance> findByDateAndDeptId(LocalDate date, Long departmentId);
	
	@Query("SELECT a FROM Attendance a INNER JOIN a.student s WHERE a.attendanceDate = :date AND s.department = :department AND s IN (:students)")
	List<Attendance> findByDateDeptAndStudents(LocalDate date, Department department, List<Student> students);

}
