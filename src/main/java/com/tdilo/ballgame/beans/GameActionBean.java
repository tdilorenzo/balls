package com.tdilo.ballgame.beans;

import com.tdilo.ballgame.login.Authenticated;
import com.tdilo.ballgame.model.Game;
import com.tdilo.ballgame.model.User;
import com.tdilo.ballgame.service.game.GameService;
import org.apache.log4j.Logger;
import org.richfaces.cdi.push.Push;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Set;

@Model
@SessionScoped
public class GameActionBean implements Serializable {

    private static final Logger log = Logger.getLogger(GameActionBean.class);
    private static final String CDI_PUSH_TOPIC = "gameUpdate";

    public String getCdiPushTopic() {
        return CDI_PUSH_TOPIC;
    }

    @Inject
    GameService gameService;

    @Inject @Authenticated
    User currentUser;


    @Inject
    @Push(topic = CDI_PUSH_TOPIC)
    @GameUpdate
    private Event<Long> gameUpdate;

    public void quit(Game game) {
        gameService.quit(game, currentUser);
        gameUpdate.fire(game.getId());
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

        gameService.pass(game, currentUser, newUser);
        gameUpdate.fire(game.getId());
    }
}
