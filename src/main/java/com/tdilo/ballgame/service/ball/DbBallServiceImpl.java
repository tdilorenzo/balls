package com.tdilo.ballgame.service.ball;

import com.tdilo.ballgame.model.Ball;
import com.tdilo.ballgame.service.DaoServiceImpl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DbBallServiceImpl extends DaoServiceImpl<Ball,Long> implements BallService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
