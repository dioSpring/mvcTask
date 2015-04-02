package org.diosoft.spring.mvcTask.services;

import org.diosoft.spring.mvcTask.model.Questionaire;
import org.diosoft.spring.mvcTask.repository.QuestionaireDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionaireServiceImpl implements QuestionaireService {

    @Autowired
    private QuestionaireDAO questionaireDAO;

    @Override
    public Questionaire get(Long id) {
        return questionaireDAO.getById(id);
    }
}
