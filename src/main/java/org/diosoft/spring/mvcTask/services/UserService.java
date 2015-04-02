package org.diosoft.spring.mvcTask.services;

import org.diosoft.spring.mvcTask.dto.LoginDto;
import org.diosoft.spring.mvcTask.model.User;

/**
 * Created by yar on 01.04.15.
 */
public interface UserService {
    void save(User user);
    User get(LoginDto login);
}
