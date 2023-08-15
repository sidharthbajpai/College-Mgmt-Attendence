package com.college.service;

import com.college.entity.Teacher;

public interface TeacherService {

	Teacher getTeacherByUsername(String usernameString);

	void saveTeacher(Teacher teacher);

}
