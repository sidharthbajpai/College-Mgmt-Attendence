package com.college.service;

import com.college.entity.Student;

public interface StudentService {
	Student getStudentByUsername(String username);

	void saveStudent(Student student);

}
