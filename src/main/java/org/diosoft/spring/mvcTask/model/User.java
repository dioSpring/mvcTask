package org.diosoft.spring.mvcTask.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotEmpty(message = "You should enter username")
    private String username;
//    @NotEmpty(message = "You should enter First Name")
    private String firstname;
//    @NotEmpty(message = "You should enter First Name")
    private String lastname;
    @NotEmpty(message = "Enter password")
    private String password;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateofbirth;

//    @OneToMany(cascade = CascadeType.ALL)
//    private Map<UUID, Questionaire> questionaireMap = new HashMap<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserSession> sessions = new HashSet<>();

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
//        this.password = UserPassword.getHash(password);
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //    public Map<UUID, Questionaire> getQuestionaireMap() {
//        return questionaireMap;
//    }
//
//    public void setQuestionaireMap(Map<UUID, Questionaire> questionaireMap) {
//        this.questionaireMap = questionaireMap;
//    }


    public Set<UserSession> getSessions() {
        return sessions;
    }

    public void setSessions(Set<UserSession> sessions) {
        this.sessions = sessions;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", password='" + password + '\'' +
                ", dateofbirth=" + dateofbirth +
                '}';
    }
}
