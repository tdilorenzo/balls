package com.tdilo.ballgame.converters;

import com.tdilo.ballgame.model.Ball;
import com.tdilo.ballgame.service.ball.BallService;
import org.apache.log4j.Logger;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@FacesConverter(forClass = Ball.class)
public class BallConverter implements Converter {

    public static final Logger log = Logger.getLogger(BallConverter.class);

    private BeanManager getBeanManager() {
        try {
            InitialContext initialContext = new InitialContext();
            return (BeanManager) initialContext.lookup("java:comp/BeanManager");
        } catch (NamingException e) {
            log.error("Couldn't get BeanManager through JNDI");
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public BallService getFacade() {
        BeanManager bm = getBeanManager();
        Bean<BallService> bean = (Bean<BallService>) bm.getBeans(BallService.class).iterator().next();
        CreationalContext<BallService> ctx = bm.createCreationalContext(bean);
        BallService dao = (BallService) bm.getReference(bean, BallService.class, ctx); // this could be inlined, but intentionally left this way
        return dao;
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Long id = Long.parseLong(s);
        Ball ball;
        ball = getFacade().get(id);
        return ball;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o instanceof Ball)
            return ((Ball) o).getId() + "";
        else if (o instanceof Long) {
            Long id = (Long) o;
            return getFacade().get(id).getId() + "";
        }
        return "";
    }
}
