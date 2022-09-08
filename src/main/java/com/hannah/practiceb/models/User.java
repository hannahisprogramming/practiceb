package com.hannah.practiceb.models;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String username;
    private String email;
    private String password;

    @JoinTable(name = "avatars", joinColumns = {
            @JoinColumn(name = "avatar_id", referencedColumnName = "id", nullable = false) })
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Avatar> avatars;

    @JoinTable(name = "potions", joinColumns = {
            @JoinColumn(name = "potion_id", referencedColumnName = "id", nullable = false) })
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Potion> potions;

    public User() {
        this.id = 01;
        this.name = "";
        this.username = "";
        this.email = "";
        this.password = "";
        this.avatars = new LinkedHashSet<Avatar>();
        this.potions = new LinkedHashSet<>();
    }

    public User(String name, String username, String email, String password) {
        this.id = 0;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatars = new LinkedHashSet<Avatar>();
        this.potions = new LinkedHashSet<>();
    }

    public void addAvatar(Avatar avatar) {
        this.avatars.add(avatar);
    }

    public void removeAvatar(Avatar avatar) {
        this.avatars.remove(avatar);
    }
}
