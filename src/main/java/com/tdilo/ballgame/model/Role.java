package com.tdilo.ballgame.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.FetchType.*;

@Entity
@Table(name="Roles")
public class Role implements Serializable {

    public static final String BALLGAME_ADMIN_ROLE = "BALLGAME_ADMIN";
    public static final String BALLGAME_USER_ROLE = "BALLGAME_USER";

    private String roleName;
    private String roleGroup;
    private Set<User> users;

    @Id
    @Column(name="role_name")
        public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Column(name="role_group")
        public String getRoleGroup() {
        return roleGroup;
    }

    public void setRoleGroup(String roleGroup) {
        this.roleGroup = roleGroup;
    }

    @ManyToMany(fetch = EAGER, cascade = DETACH)
    @JoinTable(name="User_Roles",
               joinColumns = {@JoinColumn(name="role_name")},
               inverseJoinColumns = {@JoinColumn(name="username")
    })
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
