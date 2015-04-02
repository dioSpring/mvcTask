package org.diosoft.spring.mvcTask.repository;

import org.diosoft.spring.mvcTask.model.UserSession;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserSessionDAOImpl implements UserSessioDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public UserSession get(Long id) {
        Session session = sessionFactory.openSession();
        UserSession userSession = (UserSession) session.load(UserSession.class, id);
        session.close();
        return userSession;
    }

    @Override
    public void save(UserSession userSession) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(userSession);
        transaction.commit();
        session.flush();
        session.close();
    }

    @Override
    public UserSession findBySessionId(String sessionId) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(UserSession.class).add(Restrictions.eq("sessionId", sessionId));
        List<UserSession> userSessions = criteria.list();
        session.close();
        if (userSessions != null && userSessions.size() > 0) return userSessions.get(0);
        return null;
    }


}
