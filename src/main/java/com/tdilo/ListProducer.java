package com.tdilo;

import com.tdilo.ballgame.model.Ball;
import com.tdilo.ballgame.model.Game;
import com.tdilo.ballgame.model.User;
import com.tdilo.ballgame.service.ball.BallService;
import com.tdilo.ballgame.service.game.GameService;
import com.tdilo.ballgame.service.user.UserService;
import com.tdilo.ballgame.login.SecurityManager;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

public class ListProducer {
    @Inject UserService userService;
    @Inject BallService ballService;
    @Inject GameService gameService;
    @Inject SecurityManager securityManager;

    @Produces
    @Named
    @RequestScoped
    @SuppressWarnings("unchecked")
    List<Ball> getBalls() {
        List<Ball> balls;
        balls = ballService.getAll();
        return balls;
    }

    @Produces
    @Named
    @RequestScoped
    @SuppressWarnings("unchecked")
    List<User> getUserList() {
        List<User> users;
        users = userService.getAllWithRoles();
        return users;
    }

    @Produces
    @Named
    @RequestScoped
    @SuppressWarnings("unchecked")
    List<Game> getAllGamesList() {
        List<Game> games;
        games = gameService.getAll();
        return games;
    }

    @Produces
    @Named
    @RequestScoped
    @SuppressWarnings("unchecked")
    List<Game> getMyGamesList() {
        User user = securityManager.getCurrentUser();
        List<Game> games;
        games = gameService.getForUser(user, false);
        return games;
    }
}
