package com.tdilo.ballgame.wizards;

import com.tdilo.ballgame.model.Ball;
import com.tdilo.ballgame.model.User;
import com.tdilo.ballgame.service.game.GameService;
import com.tdilo.ballgame.webwizard.AbstractWizard;
import com.tdilo.ballgame.webwizard.View;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named("createGameWizard")
public class CreateGameWizard extends AbstractWizard {

    private static final String WIZARD = "/user/";
    private static final String REDIRECT = "?faces-redirect=true";
    public static final String FIRST_STEP = WIZARD + "create-game-opponents" + REDIRECT;
    public static final String SECOND_STEP = WIZARD + "create-game-ball" + REDIRECT;
    public static final String SUMMARY = WIZARD + "create-game-summary" + REDIRECT;
    public static final String WIZARD_FINISH = WIZARD + "main" + REDIRECT;
    public static final String WIZARD_CANCEL = WIZARD + "main" + REDIRECT;

    @Inject
    private GameService gameService;

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

    protected List<View> prepareViews() {
        List<View> views = new ArrayList<View>();
        views.add(new View("Select Opponents", FIRST_STEP));
        views.add(new View("Choose Ball", SECOND_STEP));
        views.add(new View("Summary", SUMMARY));
        return views;
    }

    protected String complete() {
        gameService.createGame(users, ball);
        return WIZARD_FINISH;
    }

    protected String clean() {
        return WIZARD_CANCEL;
    }
}