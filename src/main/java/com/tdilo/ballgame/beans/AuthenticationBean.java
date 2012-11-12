package com.tdilo.ballgame.beans;

import com.tdilo.ballgame.login.Login;
import com.tdilo.ballgame.login.Logout;
import com.tdilo.ballgame.model.Role;
import com.tdilo.ballgame.model.User;
import com.tdilo.ballgame.service.user.UserService;
import org.apache.log4j.Logger;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


@Model
public class AuthenticationBean {

    private static final String REDIRECT = "?faces-redirect=true";
    private static final Logger log = Logger.getLogger(AuthenticationBean.class);

    @Inject
    HttpServletRequest httpServletRequest;

    @Inject
    UserService userService;

    @Inject
    @Login
    Event<User> onLogin;

    @Inject
    @Logout
    Event<String> onLogout;

    String username, password;

    public String login() throws ServletException {
        httpServletRequest.login(username, password);
        if (httpServletRequest.getUserPrincipal() != null) {
            User user = userService.getWithGames(username);
            onLogin.fire(user);
            //if (httpServletRequest.isUserInRole(BALLGAME_ADMIN_ROLE))
            //    return "/admin/main";
            //else
            return "/user/main" + REDIRECT;
        }
        return "/index" + REDIRECT;
    }

    public String logout() throws ServletException {
        httpServletRequest.logout();
        onLogout.fire("");
        return "/index" + REDIRECT;
    }

    public String getUsername() {
        return (null == username) ? "" : username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return (null == password) ? "" : password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdminRoleName(){
        return Role.BALLGAME_ADMIN_ROLE;
    }

}
