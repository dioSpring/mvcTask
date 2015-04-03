package org.diosoft.spring.mvcTask.model;

import javax.persistence.*;

@Entity
public abstract class Questionaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private QuestionaireType type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionaireType getType() {
        return type;
    }

    public void setType(QuestionaireType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Questionaire{" +
                "id=" + id +
                ", type=" + type +
                '}';
    }
}