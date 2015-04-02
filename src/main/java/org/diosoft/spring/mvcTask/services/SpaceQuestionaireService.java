package org.diosoft.spring.mvcTask.services;

import org.diosoft.spring.mvcTask.model.SpaceQuestionaire;

/**
 * Created by yar on 02.04.15.
 */
public interface SpaceQuestionaireService {
    void save(SpaceQuestionaire spaceQuestionaire);
    SpaceQuestionaire get(Long id);
}
