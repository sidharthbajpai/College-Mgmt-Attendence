package com.college.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.college.enums.RequestStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long requestId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String phoneNumber;
    private String location;
    private String email;
    @Enumerated(EnumType.STRING)
    private RequestStatus status;
    
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
