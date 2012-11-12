package com.tdilo.ballgame.webwizard;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@ConversationScoped
public abstract class AbstractWizard implements Serializable {
    @Inject private Conversation conversation;
    @Inject private ViewManager viewManager;

    protected abstract List<View> prepareViews();

    protected abstract String complete();

    protected abstract String clean();

    public String start() {
        beginConversation();
        viewManager.setViews(prepareViews());
        return next();
    }

    public String finish() {
        endConversation();
        return complete();
    }

    public String cancel() {
        endConversation();
        return clean();
    }

    public String next() {
        View view = viewManager.getNextView();
        view.setAvailable(true);
        return view.getOutcome();
    }

    public String previous() {
        return viewManager.getPreviousView().getOutcome();
    }

    public String getViewAt(int index) {
        return viewManager.getViewAt(index).getOutcome();
    }

    public List<View> getViews() {
        return viewManager.getViews();
    }

    public boolean isFirst() {
        return viewManager.isFirst();
    }

    public boolean isLast() {
        return viewManager.isLast();
    }

    public boolean isStartable(){
        return (conversation == null) || conversation.isTransient();
    }

    private void beginConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
            return;
        }
        throw new IllegalStateException();
    }

    private void endConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
            return;
        }
        throw new IllegalStateException();
    }
}