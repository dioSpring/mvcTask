package org.diosoft.spring.mvcTask.repository;

import org.diosoft.spring.mvcTask.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yar on 29.03.15.
 */
@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(user);

        transaction.commit();
        session.close();
    }

    @Override
    public User get(Long id) {
        Session session = sessionFactory.openSession();
        User user = (User) session.load(User.class, id);
        session.close();
        return user;
    }

    @Override
    public User findByUsername(String username) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("username", username));
        List<User> users = criteria.list();
        if (users != null && users.size() > 0) {
            User user = users.get(0);
            session.merge(user);
            session.close();
            return user;
        }
        session.close();
        return null;
    }

    @Override
    public void remove(Long id) {
//        Session session  = sessionFactory.openSession();
//        session.createQuery("delete from User where u.id").setParameter()
//        User user = (User) session.load(User.class, id);
//        session.close();
        return;
    }

    @Override
    public boolean exist(Long id) {
        return false;
    }
}
