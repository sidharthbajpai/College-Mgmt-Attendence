package com.college.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.college.enums.AttendanceStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Attendance {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long attendanceId;
	
	private LocalDate attendanceDate;
	@Enumerated(EnumType.STRING)
	private AttendanceStatus status;
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;
}
