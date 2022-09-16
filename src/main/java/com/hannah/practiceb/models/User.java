package com.hannah.practiceb.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
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

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Avatar> avatars;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Potion> potions;

    /**
     * Convert a UserPassDTO into a User
     * @author Hannah Bush
     * @param //dto
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
