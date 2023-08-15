package com.college.service;

import com.college.entity.Admin;

public interface AdminService {

	void saveAdmin(Admin admin);

	Admin getAdminByUsername(String usernameString);

}
