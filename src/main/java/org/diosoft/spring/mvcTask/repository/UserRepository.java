package org.diosoft.spring.mvcTask.repository;

import org.diosoft.spring.mvcTask.model.User;

import java.util.UUID;

/**
 * Created by yar on 26.03.15.
 */
public interface UserRepository {
    void add(User user);
    User get(UUID userId);
    void remove(UUID userId);
    boolean exist(UUID userId);
}
