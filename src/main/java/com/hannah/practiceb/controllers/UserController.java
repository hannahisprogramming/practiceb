package com.hannah.practiceb.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hannah.practiceb.annotations.Authorized;
import com.hannah.practiceb.dtos.UserDTO;
import com.hannah.practiceb.exceptions.EmailAlreadyExistsException;
import com.hannah.practiceb.exceptions.RecordNotFoundException;
import com.hannah.practiceb.exceptions.UsernameAlreadyExistsException;
import com.hannah.practiceb.models.Avatar;
import com.hannah.practiceb.models.Potion;
import com.hannah.practiceb.models.User;
import com.hannah.practiceb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private ObjectMapper objMapper;

    @Autowired
    Environment env;

    public UserController(UserService userService) {
        this.userService = userService;
        this.objMapper = new ObjectMapper();
    }

    /**
     * Get the user by id. Returns a 404 if the user cannot be found.
     *
     * @param id
     * @return
     */
    @Authorized
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable long id) {
        // Finding a user by id from the repository returns an optional user.
        Optional<User> optUser = Optional.ofNullable(userService.findById(id));

        // If user is not null, then send a 200 with the user object.
        if (optUser.isPresent()) {
            UserDTO user = new UserDTO(optUser.get());
            return ResponseEntity.ok(user);
        }

        // Otherwise, send 404
        return ResponseEntity.notFound().build();
    }

    /**
     * Get the user's avatars. Returns a 404 if the user cannot be found.
     *
     * @param id
     * @return
     */
    @Authorized
    @GetMapping("/{id}/avatars")
    public ResponseEntity<Set<Avatar>> getAvatars(@PathVariable long id) {
        // Finding a user by id from the repository returns an optional user.
        User optUser = userService.findById(id);

        // If user is not null, then send a 200 with the user object.
        if (optUser.getId() == id) {
            Set<Avatar> avatars = userService.getAvatars(optUser);
            return ResponseEntity.ok(avatars);
        }

        // Otherwise, send 404
        return ResponseEntity.notFound().build();
    }

    // Add avatar to the logged-in user
    @PostMapping("/{userId}/create/{avatarId}")
    public ResponseEntity<UserDTO> addAvatar(@PathVariable("userId") Long userId,
                                             @PathVariable("avatarId") Long avatarId)
            throws RecordNotFoundException {
        UserDTO result = null;
        try {
            result = userService.addAvatar(userId, avatarId);
            if (result != null) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (RecordNotFoundException e) {
            throw new RecordNotFoundException("Could not find user!");
        }
    }

    @DeleteMapping("/{userId}/delete/{avatarId}")
    public ResponseEntity<UserDTO> removeAvatar(@PathVariable("userId") Long userId,
                                                @PathVariable("avatarId") Long avatarId)
                                                throws RecordNotFoundException {
        UserDTO result = null;
        try {
            result = userService.removeAvatar(userId, avatarId);
            if (result != null) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (RecordNotFoundException e) {
            throw new RecordNotFoundException(e);
        }
    }

    /**
     * Get the user's potions. Returns a 404 if the user cannot be found.
     *
     * @param id
     * @return
     */
    @Authorized
    @GetMapping("/{id}/avatars")
    public ResponseEntity<Set<Potion>> getPotions(@PathVariable long id) {
        // Finding a user by id from the repository returns an optional user.
        User optUser = userService.findById(id);

        // If user is not null, then send a 200 with the user object.
        if (optUser.getId() == id) {
            Set<Potion> potions = userService.getPotions(optUser);
            return ResponseEntity.ok(potions);
        }

        // Otherwise, send 404
        return ResponseEntity.notFound().build();
    }

    // Add follower to the logged in user
    @PostMapping("/{userId}/add/{potionId}")
    public ResponseEntity<UserDTO> addPotion(@PathVariable("userId") Long userId,
                                             @PathVariable("potionId") Long potionId)
            throws RecordNotFoundException {
        UserDTO result = null;
        try {
            result = userService.addPotion(userId, potionId);
            if (result != null) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (RecordNotFoundException e) {
            throw new RecordNotFoundException("Could not find user!");
        }
    }

    @DeleteMapping("/{userId}/delete/{potionId}")
    public ResponseEntity<UserDTO> removePotion(@PathVariable("userId") Long userId,
                                                @PathVariable("potionId") Long potionId)
            throws RecordNotFoundException {
        UserDTO result = null;
        try {
            result = userService.removePotion(userId, potionId);
            if (result != null) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (RecordNotFoundException e) {
            throw new RecordNotFoundException(e);
        }
    }

    /**
     * Update a user's information based on a given user object
     *
     * @param updatedUser
     * @return a UserMiniDTO object
     * @throws EmailAlreadyExistsException
     * @throws UsernameAlreadyExistsException
     * @throws RecordNotFoundException
     */
    @Authorized
    @PostMapping("/update/profile")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO updatedUser)
            throws EmailAlreadyExistsException, UsernameAlreadyExistsException, RecordNotFoundException {
        // Pass object to service layer
        try {
            User result = userService.update(updatedUser);

            // Assuming an exception is not thrown, remove unnecessary data and return it
            // with a status of 200
            UserDTO bodyDTO = new UserDTO(result);
            return ResponseEntity.ok(bodyDTO);
        } catch (RecordNotFoundException e) {
            throw new RecordNotFoundException("User " + updatedUser.getUsername() + " does not exist!");
        } catch (UsernameAlreadyExistsException e) {
            throw new UsernameAlreadyExistsException("Username " + updatedUser.getUsername() + " already exists!", e);
        } catch (EmailAlreadyExistsException e) {
            throw new EmailAlreadyExistsException("Email " + updatedUser.getEmail() + " already exists!", e);
        }
    }
}
