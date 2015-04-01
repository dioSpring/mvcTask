package org.diosoft.spring.mvcTask.dao;

import org.diosoft.spring.mvcTask.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public UserDAO() {
	}

	public void save(User user){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(user);
		tx.commit();
		session.close();
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void SessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
