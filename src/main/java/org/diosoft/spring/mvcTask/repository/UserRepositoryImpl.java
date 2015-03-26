package org.diosoft.spring.mvcTask.repository;

import org.diosoft.spring.mvcTask.model.User;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by yar on 26.03.15.
 */
@Component
public class UserRepositoryImpl implements UserRepository {
    private Map<java.util.UUID, User> userMap = new HashMap<>();

    @Override
    public void add(User user) {
        if(!exist(user.getId())){
            userMap.put(user.getId(), user);
        }
    }

    @Override
    public User get(java.util.UUID userId) {
        return userMap.get(userId);
    }

    @Override
    public void remove(UUID userId) {
        if(exist(userId)){
            userMap.remove(userId);
        }
    }

    @Override
    public boolean exist(UUID userId) {
        return userMap.containsKey(userId);
    }
}