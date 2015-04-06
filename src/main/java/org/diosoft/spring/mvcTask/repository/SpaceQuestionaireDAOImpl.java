package org.diosoft.spring.mvcTask.repository;

import org.diosoft.spring.mvcTask.model.SpaceQuestionaire;
import org.diosoft.spring.mvcTask.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SpaceQuestionaireDAOImpl implements SpaceQuestionaireDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(SpaceQuestionaire spaceQuestionaire) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(spaceQuestionaire);
        transaction.commit();
        session.flush();
        session.close();
    }

    @Override
    public SpaceQuestionaire get(Long id) {
        Session session = sessionFactory.openSession();
        SpaceQuestionaire spaceQuestionaire = (SpaceQuestionaire) session.load(SpaceQuestionaire.class, id);
        session.close();
        return spaceQuestionaire;
    }
}
