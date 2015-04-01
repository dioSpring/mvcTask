package org.diosoft.spring.mvcTask.repository;

import org.diosoft.spring.mvcTask.model.User;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by yar on 26.03.15.
 */
@Component
public class UserRepositoryImpl implements UserRepository {
    private Map<String, User> userMap = new HashMap<>();

	@Override
	public void add(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User get(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(String userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean exist(String userId) {
		// TODO Auto-generated method stub
		return false;
	}

   
}