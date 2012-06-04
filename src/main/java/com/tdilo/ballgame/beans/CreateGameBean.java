package com.tdilo.ballgame.beans;

import com.tdilo.ballgame.model.Ball;
import com.tdilo.ballgame.model.User;
import com.tdilo.ballgame.service.game.GameService;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class CreateGameBean {

    @Inject
    GameService gameService;

    public String createGame(){
        gameService.createGame(users, ball);
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
