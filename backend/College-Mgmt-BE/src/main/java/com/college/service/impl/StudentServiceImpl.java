package com.college.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.entity.Student;
import com.college.repository.StudentRepository;
import com.college.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student getStudentByUsername(String username) {
		return studentRepository.findFirstByUsername(username).get();
	}
	
	@Override
	public void saveStudent(Student student) {
		studentRepository.save(student);
	}

}
