package com.tdilo.ballgame.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.FetchType.*;

@Entity
@Table(name = "Users")
public class User implements Serializable, Comparable {

    private String username;
    private String password;
    private String passwordHash;
    private String displayName;
    private Set<Role> roles;
    private Set<Game> games;

    @Id
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "password_hash")
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Column(name = "display_name")
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @ManyToMany(fetch = LAZY, cascade = REMOVE)
    @JoinTable(name = "User_Roles",
            joinColumns = {@JoinColumn(name = "username")},
            inverseJoinColumns = {@JoinColumn(name = "role_name")
            })
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @ManyToMany(fetch = LAZY, cascade = DETACH)
    @JoinTable(name = "User_Games",
            joinColumns = {@JoinColumn(name = "username")},
            inverseJoinColumns = {@JoinColumn(name = "game_id")
            })
    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    @Transient
    public List<Game> getGamesAsList() {
        List<Game> games;
        games = new ArrayList<Game>(getGames());
        return games;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        User u = (User) obj;
        return this.username.equalsIgnoreCase(u.username);
    }

    @Override
    public int compareTo(Object o) {
        if (o == null || !(o instanceof User))
            return -1;
        if (this.equals(o))
            return 0;

        return this.username.compareTo(((User) o).username);
    }
}
