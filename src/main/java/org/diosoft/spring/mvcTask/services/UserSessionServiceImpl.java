package org.diosoft.spring.mvcTask.services;

import org.diosoft.spring.mvcTask.model.UserSession;
import org.diosoft.spring.mvcTask.repository.UserSessioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSessionServiceImpl implements UserSessionService{
    @Autowired
    private UserSessioDAO userSessioDAO;

    @Override
    public void save(UserSession userSession){
        userSessioDAO.save(userSession);
    }

    @Override
    public UserSession find(String sessionId) {
        return userSessioDAO.findBySessionId(sessionId);
    }

    @Override
    public boolean isActive(String sessionId) {
        boolean isActive=false;
        UserSession userSession = find(sessionId);
        System.out.println(userSession);
        if(userSession!=null) isActive=true;
        return isActive;
    }


}
