package com.tdilo.ballgame.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.FetchType.EAGER;

@Entity
public class Game implements Serializable {

    private long id;
    private Date startTime;
    private Date endTime;
    private String ballCarrier;
    private Ball gameBall;
    private Set<User> users;
    private int round;

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name="start_time")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Column(name="end_time")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Column(name="ball_carrier")
    public String getBallCarrier() {
        return ballCarrier;
    }

    public void setBallCarrier(String ballCarrier) {
        this.ballCarrier = ballCarrier;
    }

    @ManyToOne(fetch = EAGER, cascade = REMOVE)
    public Ball getGameBall() {
        return gameBall;
    }

    public void setGameBall(Ball gameBall) {
        this.gameBall = gameBall;
    }

    @ManyToMany(fetch = EAGER, cascade = DETACH)
    @JoinTable(name="User_Games",
               joinColumns = {@JoinColumn(name="game_id")},
               inverseJoinColumns = {@JoinColumn(name="username")
    })
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Transient
    @SuppressWarnings("unchecked")
    public List<User> getUsersAsList(){
        ArrayList<User> list;
        list = new ArrayList<User>(getUsers());
        Collections.sort(list);
        return list;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }
}
