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
public class AttendanceDetailsInDto {
	private Long attendanceId;
	private AttendanceStatus status;
	private Long studentId;
}
