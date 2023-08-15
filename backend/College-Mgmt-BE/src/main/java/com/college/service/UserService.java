package com.college.service;

import com.college.entity.User;

public interface UserService {

	void saveUser(User user);

	User getUserByUsername(String username);

}
