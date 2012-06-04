package com.tdilo.ballgame;

import org.richfaces.cdi.push.Push;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author <a href="http://community.jboss.org/people/lfryc">Lukas Fryc</a>
 */
@Named
@RequestScoped
public class PushBean {
    public static final String PUSH_CDI_TOPIC = "pushCdi";
    private String message;
    @Inject @Push(topic = PUSH_CDI_TOPIC) Event<String> pushEvent;

    /**
     * Sends message.      *      * @param message to send
     */
    public void sendMessage() {
        pushEvent.fire(message);
        message = "";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
