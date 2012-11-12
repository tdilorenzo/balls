package com.tdilo.ballgame;

import org.apache.log4j.Logger;
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

    private static final Logger log = Logger.getLogger(PushBean.class);

    public static final String CDI_TOPIC = "smudge";

    public String getCdiTopic() {
        return CDI_TOPIC;
    }

    private String message;

    @Inject
    @Push(topic = CDI_TOPIC)
    Event<String> pushEvent;

    /**
     * Sends message.      *      * @param message to send
     */
    public void sendMessage() {
        log.info("pushing message " + message);
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
