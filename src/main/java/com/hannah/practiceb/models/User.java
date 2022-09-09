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

    @JoinTable(name = "avatars", joinColumns = {
            @JoinColumn(name = "avatar_id", referencedColumnName = "id", nullable = false) })
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Avatar> avatars;

    @JoinTable(name = "potions", joinColumns = {
            @JoinColumn(name = "potion_id", referencedColumnName = "id", nullable = false) })
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Potion> potions;

//    public User(UserMiniDTO dto) {
//        this.id = dto.getId();
//        this.username = dto.getUsername();
//    }

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
}
