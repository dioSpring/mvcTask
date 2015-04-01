package org.diosoft.spring.mvcTask.services;

import org.diosoft.spring.mvcTask.model.UserSession;

/**
 * Created by yar on 01.04.15.
 */
public interface UserSessionService {
    void save(UserSession userSession);
    UserSession find(String sessionId);
}
