package com.college.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long departmentId;
	
	private String departmentName;
	
	@OneToMany(mappedBy = "department")
	private List<Student> students;
	
	@OneToMany(mappedBy = "department")
	private List<Teacher> teachers;
}
