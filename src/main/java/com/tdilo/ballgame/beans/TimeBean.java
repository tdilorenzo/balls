package com.tdilo.ballgame.beans;

import javax.inject.Named;
import java.util.Calendar;

@Named
public class TimeBean {
      String date;

    public String getDate() {
        return Calendar.getInstance().getTime().toString();
    }
}
