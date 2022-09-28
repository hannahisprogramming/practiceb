package com.hannah.practiceb.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Avatar> avatars;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Potion> potions;

    public void addAvatar(Avatar avatar) {
        this.avatars.add(avatar);
    }

    public void removeAvatar(Avatar avatar) {
        if (avatars.contains(avatar)) {
            this.avatars.remove(avatar);
        }
    }

    public void addPotion(Potion potion) {
        this.potions.add(potion);
    }

    public void removePotion(Potion potion) {
        if (potions.contains(potion)) {
            this.potions.remove(potion);
        }
    }
}
