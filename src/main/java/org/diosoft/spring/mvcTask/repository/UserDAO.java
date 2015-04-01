package org.diosoft.spring.mvcTask.repository;

import org.diosoft.spring.mvcTask.model.User;

import java.util.UUID;

/**
 * Created by yar on 29.03.15.
 */
public interface UserDAO {
    void save(User user);
    User get(Long id);
    User findByUsername(String username);
    void remove(Long id);
    boolean exist(Long id);
}
