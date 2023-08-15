package com.college.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
	private Long studentId;
	
	private String firstName;
    private String lastName;
    private String username;
    private String phoneNumber;
    private String location;
    private String email;
    private String departmentName;
}
