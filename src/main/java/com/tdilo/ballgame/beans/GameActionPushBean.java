package com.tdilo.ballgame.beans;

import com.tdilo.ballgame.login.Authenticated;
import com.tdilo.ballgame.model.User;
import org.richfaces.application.push.MessageException;
import org.richfaces.cdi.push.Push;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class GameActionPushBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String userIdentifier;
    private String message;

    @Inject @Authenticated
    User currentUser;

    @Inject
    @Push(topic = "")
    @GameUpdate
    private Event<String> pushEvent;

    /*@Inject
    public void init() {

        if (userIdentifier == null) {
            userIdentifier = currentUser.getUsername();
        }

        TopicsContext topicsContext = TopicsContext.lookup();
        topicsContext.getOrCreateTopic(new TopicKey(CDI_PUSH_TOPIC, userIdentifier));//initialize the topic and make the troublesome message disappears
    }*/

    public void sendMessage() throws MessageException {
        pushEvent.fire(message);
    }


    /*public String getUserIdentifier() {
        return userIdentifier;
    }

    public void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = userIdentifier;
    }*/

    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }

}
