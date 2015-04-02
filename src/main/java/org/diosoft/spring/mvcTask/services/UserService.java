package org.diosoft.spring.mvcTask.services;

import org.diosoft.spring.mvcTask.model.User;

/**
 * Created by yar on 01.04.15.
 */
public interface UserService {
    void save(User user);
    User get(String  name,String pass);
    boolean checkUserName(String  name);
}
