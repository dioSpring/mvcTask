package org.diosoft.spring.mvcTask.repository;

import org.diosoft.spring.mvcTask.model.UserSession;

import java.util.UUID;

/**
 * Created by yar on 29.03.15.
 */
public interface UserSessioDAO {
    UserSession get(Long id);
    void save(UserSession userSession);
    UserSession findBySessionId(String sessionId);
}
