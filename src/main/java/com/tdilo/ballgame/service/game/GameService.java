package com.tdilo.ballgame.service.game;

import com.tdilo.ballgame.model.Ball;
import com.tdilo.ballgame.model.Game;
import com.tdilo.ballgame.model.User;
import com.tdilo.ballgame.service.DaoService;

import java.util.List;

public interface GameService extends DaoService<Game, Long> {
    void createGame(User [] users, Ball ball);
    void quit(Game game, User user);
    void pass(Game game, User from, User to);
    List<Game> getForUser(User user, boolean includeEnded);
}
