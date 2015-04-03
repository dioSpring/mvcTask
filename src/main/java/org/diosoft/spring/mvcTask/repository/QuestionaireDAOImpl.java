package org.diosoft.spring.mvcTask.repository;

import org.diosoft.spring.mvcTask.model.Questionaire;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionaireDAOImpl implements QuestionaireDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Questionaire getById(Long id) {
        Session session = sessionFactory.openSession();
        Questionaire questionaire = (Questionaire) session.load(Questionaire.class, id);
        session.close();
        return questionaire;
    }
}
