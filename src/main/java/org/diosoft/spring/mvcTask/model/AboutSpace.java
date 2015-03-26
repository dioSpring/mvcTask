package org.diosoft.spring.mvcTask.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Created by yar on 26.03.15.
 */
public class AboutSpace implements Questionaire {
    private UUID id;
    public static final String DESCRIPTION = "About Space";
    public static final String BASEURL = "/aboutspace";
    @NotEmpty(message = "Write some words")
    private String aboutSpace;
    @NotNull(message = "Write years")
    private Integer earthOld;

    public AboutSpace() {
        this.id=UUID.randomUUID();
    }

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
    public String getBaseUrl() {
        return BASEURL;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String toString() {
        return "AboutSpace{" +
                "id=" + id +
                ", aboutSpace='" + aboutSpace + '\'' +
                ", earthOld=" + earthOld +
                '}';
    }
}
