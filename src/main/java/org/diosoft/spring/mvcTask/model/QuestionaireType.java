package org.diosoft.spring.mvcTask.model;

public enum QuestionaireType {
    SPACE("/space", "About space");

    private String baseUrl;
    private String description;

    QuestionaireType(String baseUrl, String description) {
        this.baseUrl = baseUrl;
        this.description = description;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getDescription() {
        return description;
    }
}
