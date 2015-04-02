package org.diosoft.spring.mvcTask.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
@Entity
public class SpaceQuestionaire extends Questionaire {
    @NotEmpty(message = "Write some words")
    private String aboutSpace;
    @NotNull(message = "Write years")
    private Integer earthOld;

    public SpaceQuestionaire() {
        setType(QuestionaireType.SPACE);
    }

    public String getAboutSpace() {
        return aboutSpace;
    }

    public void setAboutSpace(String aboutSpace) {
        this.aboutSpace = aboutSpace;
    }

    public Integer getEarthOld() {
        return earthOld;
    }

    public void setEarthOld(Integer earthOld) {
        this.earthOld = earthOld;
    }

    @Override
    public String toString() {
        return "SpaceQuestionaire{" +
                super.toString()+
                "aboutSpace='" + aboutSpace + '\'' +
                ", earthOld=" + earthOld +
                '}';
    }
}
