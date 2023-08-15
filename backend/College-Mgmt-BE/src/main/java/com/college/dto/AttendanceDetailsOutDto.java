package com.college.dto;

import com.college.enums.AttendanceStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDetailsOutDto {
	private Long attendanceId;
	private AttendanceStatus status;
	private Long studentId;
	private String studentFirstName;
	private String studentLastName;
}
