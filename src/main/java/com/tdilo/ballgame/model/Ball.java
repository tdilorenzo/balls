package com.tdilo.ballgame.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Ball implements Serializable {

    private long id;
    private String description;
    private String ballType;

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name="description")
        public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name="ball_type")
    public String getBallType() {
        return ballType;
    }

    public void setBallType(String ballType) {
        this.ballType = ballType;
    }


    @Transient
    public String getImage(){
        return getBallType().toLowerCase() + ".png";
    }

    @Override
    public boolean equals(Object obj) {
        if ( !(obj instanceof Ball)) {
            return false;
        }
        Ball ball = (Ball) obj;
        return this.id == ball.id;
    }
}
