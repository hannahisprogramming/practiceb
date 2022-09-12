package com.hannah.practiceb.dtos;

import com.hannah.practiceb.models.Avatar;
import com.hannah.practiceb.models.Potion;
import com.hannah.practiceb.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.LinkedHashSet;

/**
 * A DTO of User that does not have the password
 * Primarily used to pass
 * the user from a request and in a response.
 *
 * @author Hannah Bush
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private long id;
    private String username;
    private String email;
    private String name;
    private Set<Avatar> avatars;
    private Set<Potion> potions;

    /**
     * This is for validating a JWT in TokenServiceImpl
     *
     * @author Hannah Bush
     */
    public UserDTO(long id, String username) {
        this.id = id;
        this.username = username;
    }

    /**
     * This is to convert a user object into a dto
     *
     * @param user
     */
    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.name = user.getName();
        this.avatars = user.getAvatars();
        this.potions = user.getPotions(); //NOSONAR
    }
}
