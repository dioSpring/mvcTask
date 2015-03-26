package org.diosoft.spring.mvcTask.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.diosoft.spring.mvcTask.model.User;
import org.diosoft.spring.mvcTask.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceInMemoryImpl implements UserService {

	private final Map<String, User> storage = new ConcurrentHashMap<>();
	
	@Override
	public User get(String userId) {
		if(storage.containsKey(userId)){
			return storage.get(userId);
		}
		return null;
	}

	@Override
	public User save(User user) {
		storage.put(user.getId().toString(), user);
		return user;
	}

	@Override	
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		for(String userId: storage.keySet()){
			users.add(storage.get(userId));
		}
		return users;
	}


}
