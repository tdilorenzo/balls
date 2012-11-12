package com.tdilo.ballgame.login;

import com.tdilo.ballgame.model.User;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class SecurityManager implements Serializable {

    User currentUser;
    
    @Produces
    @Named
    @Authenticated
    public User getCurrentUser(){
        return currentUser;
    }


    public void onLogin(@Observes @Login User user){
        currentUser = user;
    }


    public void onLogout(@Observes @Logout String foo){
        currentUser = null;
    }
}
