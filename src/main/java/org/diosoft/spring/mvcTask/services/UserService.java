package org.diosoft.spring.mvcTask.services;

import java.util.List;

import org.diosoft.spring.mvcTask.model.User;

public interface UserService {

	User get(String userId);
	
	User save(User user);

	List<User> getAllUsers();
}
