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

//    public User(UserMiniDTO dto) {
//        this.id = dto.getId();
//        this.username = dto.getUsername();
//    }

    /**
     * Convert a UserPassDTO into a User
     * @author Hannah Bush
     * @param dto
     */
//    public User(UserPassDTO dto) {
//        this.id = dto.getId();
//        this.name = dto.getName;
//        this.username = dto.getUsername();
//        this.email = dto.getEmail();
//        this.password = dto.getPassword();
//
//        this.avatars = new LinkedHashSet<>();
//        for (UserMiniDTO avatar : dto.getAvatars()) {
//            avatars.add(new Avatar(avatar));
//        }
//
//        this.potions = new LinkedHashSet<>();
//        for (UserMiniDTO potion : dto.getPotions()) {
//            potions.add(new User(potion));
//        }
//    }

    public void addAvatar(Avatar avatar) {
        this.avatars.add(avatar);
    }

    public void removeAvatar(Avatar avatar) {
        this.avatars.remove(avatar);
    }

    public void addPotion(Potion potion) {
        this.potions.add(potion);
    }

    public void removePotion(Potion potion) {
        this.potions.remove(potion);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Avatar> getAvatars() {
        return avatars;
    }

    public void setAvatars(Set<Avatar> avatars) {
        this.avatars = avatars;
    }

    public Set<Potion> getPotions() {
        return potions;
    }

    public void setPotions(Set<Potion> potions) {
        this.potions = potions;
    }
}
