package com.college.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequestInDto {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String phoneNumber;
    private String location;
    private String email;
    private Long departmentId;
    private Long roleId;
}
