package org.diosoft.spring.mvcTask.repository;

import org.diosoft.spring.mvcTask.model.SpaceQuestionaire;

/**
 * Created by yar on 02.04.15.
 */
public interface SpaceQuestionaireDAO {
    void save(SpaceQuestionaire spaceQuestionaire);
    SpaceQuestionaire get(Long id);
}
