package com.tdilo.ballgame.beans;

import com.tdilo.ballgame.service.user.UserService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class CreateUserBean {

    @Inject
    UserService userService;

    public String createUser(){
        userService.createUser(username, displayName, password);
        return "list";
    }

    String username, password, displayName;

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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
