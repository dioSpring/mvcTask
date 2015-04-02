package org.diosoft.spring.mvcTask.repository;

import org.diosoft.spring.mvcTask.dto.LoginDto;
import org.diosoft.spring.mvcTask.model.User;

/**
 * Created by yar on 29.03.15.
 */
public interface UserDAO {
    void save(User user);
    User get(Long id);
    User findByUser(LoginDto loginDTO);
}
