package com.tdilo.ballgame.service.user;

import com.tdilo.ballgame.model.User;
import com.tdilo.ballgame.service.DaoService;

import java.util.List;

public interface UserService extends DaoService<User,String> {
    void createUser(String username, String displayName, String password);
    User getWithGames(String username);
    List<User> getAllWithRoles();
}
