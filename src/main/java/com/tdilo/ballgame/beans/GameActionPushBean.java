package com.tdilo.ballgame.beans;

import org.richfaces.application.push.MessageException;
import org.richfaces.application.push.TopicKey;
import org.richfaces.application.push.TopicsContext;
import org.richfaces.cdi.push.Push;

import javax.enterprise.event.Event;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.UUID;

@Named
@SessionScoped
public class GameActionPushBean {

    private static final long serialVersionUID = 1L;
    private static final String CDI_PUSH_TOPIC = "pushCdi";
    private String userIdentifier;
    private String message;

    @Inject
    @Push(topic = CDI_PUSH_TOPIC, subtopic = "#{gameActionBean.userIdentifier}")
    private Event<String> pushEvent;

    @Inject
    public void init() {

        if (userIdentifier == null) {
            userIdentifier = UUID.randomUUID().toString().replace("-", "");
        }

        TopicsContext topicsContext = TopicsContext.lookup();
        topicsContext.getOrCreateTopic(new TopicKey(CDI_PUSH_TOPIC, userIdentifier));//initialize the topic and make the troublesome message disappears
    }

    public void sendMessage() throws MessageException {
        pushEvent.fire(message);
    }


    public String getUserIdentifier() {
        return userIdentifier;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }

}
