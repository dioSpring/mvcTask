package org.diosoft.spring.mvcTask.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;

public class User {

    private UUID id;
    @NotEmpty(message = "You should enter First Name")
    private String firstname;
    @NotEmpty(message = "You should enter First Name")
    private String lastname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateofbirth;

    private Map<UUID, Questionaire> questionaireMap = new HashMap<>();

    public User() {
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public Map<UUID, Questionaire> getQuestionaireMap() {
        return questionaireMap;
    }

    public void setQuestionaireMap(Map<UUID, Questionaire> questionaireMap) {
        this.questionaireMap = questionaireMap;
    }
}
