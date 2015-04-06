package org.diosoft.spring.mvcTask.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class UserSession {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sessionId;

//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;
    private Date lastConnected;

    public UserSession() {
        lastConnected=new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getLastConnected() {
        return lastConnected;
    }

    public void setLastConnected(Date lastConnected) {
        this.lastConnected = lastConnected;
    }


    @Override
    public String toString() {
        return "UserSession{" +
                "id=" + id +
                ", sessionId='" + sessionId + '\'' +
                ", user=" + user +
                ", lastConnected=" + lastConnected +
                '}';
    }
}
