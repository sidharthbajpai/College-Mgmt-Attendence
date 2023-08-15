package com.college.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.entity.Admin;
import com.college.repository.AdminRepository;
import com.college.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminRepository adminRepository;

	@Override
	public Admin getAdminByUsername(String usernameString) {
		return adminRepository.findAdminByUsername(usernameString).get();
	}
	
	@Override
	public void saveAdmin(Admin admin) {
		adminRepository.save(admin);
	}

	
}
