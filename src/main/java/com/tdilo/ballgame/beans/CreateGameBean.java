package com.tdilo.ballgame.beans;

import com.tdilo.ballgame.model.Ball;
import com.tdilo.ballgame.model.Game;
import com.tdilo.ballgame.model.User;
import com.tdilo.ballgame.service.game.GameService;
import org.richfaces.cdi.push.Push;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class CreateGameBean {

    private static final String CDI_PUSH_TOPIC = "gameUpdate";

    @Inject
    @Push(topic = CDI_PUSH_TOPIC)
    @GameUpdate
    private Event<Long> gameUpdate;

    @Inject
    GameService gameService;

    public String createGame(){
        Game game = gameService.createGame(users, ball);
        gameUpdate.fire(game.getId());
        return "list";
    }

    User[] users;
    Ball ball;

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }
}
