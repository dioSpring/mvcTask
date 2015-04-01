package org.diosoft.spring.mvcTask.dto;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by yar on 29.03.15.
 */
public class LoginDto {
    @NotEmpty(message = "Enter username")
    private String username;
    @NotEmpty(message = "Enter password")
    private String password;

    public LoginDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
