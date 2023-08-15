package com.college.dto;

import com.college.enums.RequestStatus;
import com.college.enums.RoleName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequestOutDto {
	private Long requestId;
    private String firstName;
    private String lastName;
    private String username;
    private String phoneNumber;
    private String location;
    private String email;
    private RequestStatus status;
    private Long departmentId;
    private String departmentName;
    private Long roleId;
    private String roleName;
}
