package com.tdilo.ballgame.beans;

import com.tdilo.ballgame.login.Authenticated;
import com.tdilo.ballgame.model.Game;
import com.tdilo.ballgame.model.User;
import com.tdilo.ballgame.service.game.GameService;
import org.apache.log4j.Logger;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.Set;

@Model
public class GameActionBean  {

    public static final Logger log = Logger.getLogger(GameActionBean.class);

    @Inject
    GameService gameService;

    @Inject @Authenticated
    User currentUser;    

    public void quit(Game game) {
        gameService.quit(game, currentUser);
    }

    public void pass(Game game) {
        Set<User> users = game.getUsers();
        User newUser;

        int sz = users.size();
        int idx;

        do {
            idx = (int) (Math.random() * sz);
            newUser = (User) users.toArray()[idx];
        } while (newUser.getUsername().equalsIgnoreCase(game.getBallCarrier()));

        log.info(currentUser.getUsername() + " passes to " + newUser.getUsername());
        gameService.pass(game, currentUser, newUser);
    }
}
