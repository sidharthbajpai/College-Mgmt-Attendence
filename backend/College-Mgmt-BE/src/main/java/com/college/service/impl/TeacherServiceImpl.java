package com.college.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.entity.Teacher;
import com.college.repository.TeacherRepository;
import com.college.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	private TeacherRepository teacherRepository;

	@Override
	public Teacher getTeacherByUsername(String username) {
		return teacherRepository.findFirstByUsername(username).get();
	}
	
	@Override
	public void saveTeacher(Teacher teacher) {
		teacherRepository.save(teacher);
	}
}
