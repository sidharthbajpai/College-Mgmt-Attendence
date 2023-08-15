package com.college.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long teacherId;
	
	private String firstName;
    private String lastName;
    private String username;
    private String phoneNumber;
    private String location;
    private String email;
    
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
