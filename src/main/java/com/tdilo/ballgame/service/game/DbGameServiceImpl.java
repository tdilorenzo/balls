package com.tdilo.ballgame.service.game;

import com.tdilo.ballgame.model.Ball;
import com.tdilo.ballgame.model.Game;
import com.tdilo.ballgame.model.User;
import com.tdilo.ballgame.service.DaoServiceImpl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Stateless
public class DbGameServiceImpl extends DaoServiceImpl<Game, Long> implements GameService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void createGame(User[] userArray, Ball ball) {
        Game game = new Game();
        game.setStartTime(new Date());
        game.setRound(1);
        game.setGameBall(ball);
        Set<User> users = new HashSet<User>();
        users.addAll(Arrays.asList(userArray));
        game.setUsers(users);

        int idx = (int) (Math.random() * userArray.length);
        String ballCarrier = userArray[idx].getUsername();

        game.setBallCarrier(ballCarrier);

        super.save(game);
    }

    @Override
    public void quit(Game game, User user) {
        game.setEndTime(new Date());
        game.setBallCarrier("");
        super.merge(game);
    }

    @Override
    public void pass(Game game, User from, User to) {
        game.setBallCarrier(to.getUsername());
        game.setRound(game.getRound() + 1);
        super.merge(game);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Game> getForUser(User user, boolean includeEnded) {
        final String hql = "SELECT g FROM Game g" +
                           "  JOIN g.users u" +
                           " WHERE u.username = :username" +
                           (includeEnded ? "" : " AND g.endTime is null");
        Query q = entityManager.createQuery(hql);
        q.setParameter("username", user.getUsername());
        List<Game> games;
        games = (List<Game>)q.getResultList();
        return games;
    }
}
