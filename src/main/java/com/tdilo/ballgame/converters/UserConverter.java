package com.tdilo.ballgame.converters;

import com.tdilo.ballgame.model.User;
import com.tdilo.ballgame.service.user.UserService;
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

@FacesConverter(forClass = User.class)
public class UserConverter implements Converter {

    public static final Logger log = Logger.getLogger(UserConverter.class);

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
    public UserService getFacade() {
        BeanManager bm = getBeanManager();
        Bean<UserService> bean = (Bean<UserService>) bm.getBeans(UserService.class).iterator().next();
        CreationalContext<UserService> ctx = bm.createCreationalContext(bean);
        UserService dao = (UserService) bm.getReference(bean, UserService.class, ctx); // this could be inlined, but intentionally left this way
        return dao;
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        User user;
        user = getFacade().get(s);
        return user;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o instanceof User)
            return ((User) o).getUsername();
        else if (o instanceof String) {
            return getFacade().get((String)o).getUsername();
        }
        return "";
    }
}
