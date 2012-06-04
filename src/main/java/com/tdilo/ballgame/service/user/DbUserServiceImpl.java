package com.tdilo.ballgame.service.user;

import com.tdilo.ballgame.model.Role;
import com.tdilo.ballgame.model.User;
import com.tdilo.ballgame.service.DaoServiceImpl;
import org.jboss.sasl.util.UsernamePasswordHashUtil;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Stateless
public class DbUserServiceImpl extends DaoServiceImpl<User, String> implements UserService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    final String SECURITY_REALM = "ballGame";
    final String DEFAULT_ROLE_GROUP = "default";

    private UsernamePasswordHashUtil pwHashUtil;

    public DbUserServiceImpl() {
        try {
            pwHashUtil = new UsernamePasswordHashUtil();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createUser(String username, String displayName, String password) {
        User user = new User();
        String passwordHash = pwHashUtil.generateHashedHexURP(username, SECURITY_REALM, password.toCharArray());
        user.setUsername(username);
        user.setPassword(password);
        user.setPasswordHash(passwordHash);
        user.setDisplayName(displayName);
        user.setRoles(getDefaultRoles());
        super.save(user);
    }

    @Override
    public User getWithGames(String username) {
        User user = super.get(username);
        // Force initialization of games collection
        user.getGames().size();
        return user;
    }

    @Override
    public List<User> getAllWithRoles() {
        List<User> users = super.getAll();
        for(User user: users)
            // Force initialization of games collection
            user.getRoles().size();
        return users;
    }

    @SuppressWarnings("unchecked")
    private Set<Role> getDefaultRoles() {
        final String hql = "SELECT r FROM Role r WHERE r.roleGroup = :defaultRoleGroup";

        Query q = getEntityManager().createQuery(hql);
        q.setParameter("defaultRoleGroup", DEFAULT_ROLE_GROUP);

        Set<Role> roles = new HashSet<Role>();
        roles.addAll((List<Role>) q.getResultList());
        return roles;
    }


}
