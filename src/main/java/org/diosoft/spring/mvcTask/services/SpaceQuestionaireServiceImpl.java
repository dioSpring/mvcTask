package org.diosoft.spring.mvcTask.services;

import org.diosoft.spring.mvcTask.model.SpaceQuestionaire;
import org.diosoft.spring.mvcTask.repository.SpaceQuestionaireDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpaceQuestionaireServiceImpl implements SpaceQuestionaireService {

    @Autowired
    private SpaceQuestionaireDAO spaceQuestionaireDAO;
    @Override
    public void save(SpaceQuestionaire spaceQuestionaire) {
        spaceQuestionaireDAO.save(spaceQuestionaire);
    }

    @Override
    public SpaceQuestionaire get(Long id) {
        return spaceQuestionaireDAO.get(id);
    }
}
