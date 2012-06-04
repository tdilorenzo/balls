package com.tdilo.ballgame.beans;

import org.apache.log4j.Logger;

import javax.enterprise.inject.Model;

@Model
public class RichBean {

    public static final Logger log = Logger.getLogger(RichBean.class);

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        log.info(this.name + "->" + name);
        this.name = name;
    }
}
