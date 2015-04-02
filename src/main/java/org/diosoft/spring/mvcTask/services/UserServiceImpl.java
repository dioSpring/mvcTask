package org.diosoft.spring.mvcTask.services;

import org.diosoft.spring.mvcTask.model.User;
import org.diosoft.spring.mvcTask.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public void save(User user) {
		userDAO.save(user);
	}

	@Override
	public User get(String name, String pass) {
		return userDAO.findUser(name, pass);
	}

	@Override
	public boolean checkUserName(String name) {
		return userDAO.findUserByName(name);
	}

}