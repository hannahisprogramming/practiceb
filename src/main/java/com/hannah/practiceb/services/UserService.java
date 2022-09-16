package com.hannah.practiceb.services;

import com.hannah.practiceb.dtos.UserDTO;
import com.hannah.practiceb.exceptions.EmailAlreadyExistsException;
import com.hannah.practiceb.exceptions.RecordNotFoundException;
import com.hannah.practiceb.exceptions.UsernameAlreadyExistsException;
import com.hannah.practiceb.models.Avatar;
import com.hannah.practiceb.models.Potion;
import com.hannah.practiceb.models.User;
import com.hannah.practiceb.repositiories.AvatarRepository;
import com.hannah.practiceb.repositiories.PotionRepository;
import com.hannah.practiceb.repositiories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private AvatarRepository avatarRepository;

    private PotionRepository potionRepository;

    /**
     * A constructor to be used to inject dependencies
     *
     * @param userRepository
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * A constructor to be used to inject dependencies
     *
     * @param avatarRepository
     */
    public void AvatarService(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    /**
     * A constructor to be used to inject dependencies
     *
     * @param potionRepository
     */
    public void PotionService(PotionRepository potionRepository) {
        this.potionRepository = potionRepository;
    }

    /**
     * Returns a user object with a given id
     *
     * @param id
     * @return
     */
    public User findById(long id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            return userOpt.get();
        }
        return null;
    }

    /**
     * Returns a user object with a given email and password
     *
     * @param email
     * @param password
     * @return
     */
    public Optional<User> findByCredentials(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    /**
     * Returns a list of all users in the database
     *
     * @return
     */
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Validates a given user object and saves the object to the database.
     *
     * @param user
     * @return
     * @throws EmailAlreadyExistsException
     * @throws UsernameAlreadyExistsException
     */
    public User save(User user) throws EmailAlreadyExistsException, UsernameAlreadyExistsException {
        Optional<User> userOpt = userRepository.findById(user.getId());

        if (userOpt.isPresent()) {
            User foundUser = userOpt.get();

            // If emails are different and if it already exists in the database, throw an EmailAlreadyExistsException.
            if (!foundUser.getEmail().equals(user.getEmail()) && userRepository.existsByEmail(foundUser.getEmail())) {
                throw new EmailAlreadyExistsException();
            }

            // If usernames are different and if it already exists in the database, throw an UsernameAlreadyExistsException.
            if (!foundUser.getUsername().equals(user.getUsername()) && userRepository.existsByUsername(foundUser.getUsername())) {
                throw new UsernameAlreadyExistsException();
            }
        }
        else {
            // If email already exists in the database, throw an EmailAlreadyExistsException.
            if (userRepository.existsByEmail(user.getEmail())) {
                throw new EmailAlreadyExistsException();
            }

            // If username already exists in the database, throw an UsernameAlreadyExistsException.
            if (userRepository.existsByUsername(user.getUsername())) {
                throw new UsernameAlreadyExistsException();
            }
        }

        /* Pass to repository and return the result */
        return userRepository.save(user);
    }

    // Returns set of avatars the user has
    public Set<Avatar> getAvatars(User user) {
        Optional<User> userOpt = userRepository.findById(Long.valueOf(user.getId()));
        if (userOpt.isPresent()) {
            return userOpt.get().getAvatars();
        }
        return new HashSet<>();
    }

    // Adds an avatar to a user's set of avatars
    public UserDTO addAvatar(long userId, long avatarId) throws RecordNotFoundException {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Avatar> avatarOpt = avatarRepository.findById(avatarId);
        User result;
        if (!userOpt.isPresent()) {
            throw new RecordNotFoundException("User not found!");
        }
        if (!avatarOpt.isPresent()) {
            throw new RecordNotFoundException("Avatar not found!");
        }

        // to see if it already exists
        try {
            User user = userOpt.get();
            Avatar avatar = avatarOpt.get();

            // Update user avatar list
            user.addAvatar(avatar);

            // Save both users
            result = userRepository.save(user);
            avatarRepository.save(avatar);
            return new UserDTO(result);
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }

    /**
     * To remove avatar from the table
     * @param userId, the user that follows
     * @param avatarId, the user's avatar
     * @return
     */
    public UserDTO removeAvatar(long userId, long avatarId) throws RecordNotFoundException {
        Optional<User> optUser = userRepository.findById(userId);
        Optional<Avatar> optAvatar = avatarRepository.findById(avatarId);
        User result;
        if (!optUser.isPresent()) {
            throw new RecordNotFoundException("Current user not found!");
        }
        if (!optAvatar.isPresent()) {
            throw new RecordNotFoundException("Target avatar not found!");
        }
        try {
            User user = optUser.get();
            Avatar avatar = optAvatar.get();

            // Update user avatar list
            user.removeAvatar(avatar);

            // Save both users
            result = userRepository.save(user);
            avatarRepository.delete(avatar);
            return new UserDTO(result);
        }catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }

    // Returns set of avatars the user has
    public Set<Potion> getPotions(User user) {
        Optional<User> userOpt = userRepository.findById(Long.valueOf(user.getId()));
        if (userOpt.isPresent()) {
            return userOpt.get().getPotions();
        }
        return new HashSet<>();
    }

    // Adds an potion to a user's set of potions
    public UserDTO addPotion(long userId, long potionId) throws RecordNotFoundException {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Potion> potionOpt = potionRepository.findById(potionId);
        User result;
        if (!userOpt.isPresent()) {
            throw new RecordNotFoundException("User not found!");
        }
        if (!potionOpt.isPresent()) {
            throw new RecordNotFoundException("Potion not found!");
        }

        // to see if it already exists
        try {
            User user = userOpt.get();
            Potion potion = potionOpt.get();

            // Update user avatar list
            user.addPotion(potion);

            // Save both users
            result = userRepository.save(user);
            potionRepository.save(potion);
            return new UserDTO(result);
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }

    /**
     * To remove avatar from the table
     * @param userId, the user that follows
     * @param potionId, the user's avatar
     * @return
     */
    public UserDTO removePotion(long userId, long potionId) throws RecordNotFoundException {
        Optional<User> optUser = userRepository.findById(userId);
        Optional<Potion> optPotion = potionRepository.findById(potionId);
        User result;
        if (!optUser.isPresent()) {
            throw new RecordNotFoundException("Current user not found!");
        }
        if (!optPotion.isPresent()) {
            throw new RecordNotFoundException("Target avatar not found!");
        }
        try {
            User user = optUser.get();
            Potion potion = optPotion.get();

            // Update user avatar list
            user.removePotion(potion);

            // Save both users
            result = userRepository.save(user);
            potionRepository.delete(potion);
            return new UserDTO(result);
        }catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }

    /**
     * Validate the information within a given user object and saves the object ot
     * the database
     *
     * @param dto
     * @return
     * @throws EmailAlreadyExistsException
     * @throws UsernameAlreadyExistsException
     * @throws RecordNotFoundException
     */
    public User update(UserDTO dto) throws EmailAlreadyExistsException, UsernameAlreadyExistsException, RecordNotFoundException {
        /* Local Variables */
        Optional<User> optUser = userRepository.findById(dto.getId());
        User user;

        /* Validate Data */

        // If user does not exist, throw an exception.
        if (!optUser.isPresent()) {
            throw new RecordNotFoundException("User is not found!");
        }

        // Unwrap the user from the optional
        user = optUser.get();

        // If emails are different and if it already exists in the database, throw an EmailAlreadyExistsException.
        if (!dto.getEmail().equals(user.getEmail()) && userRepository.existsByEmail(dto.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        // If usernames are different and if it already exists in the database, throw an UsernameAlreadyExistsException.
        if (!dto.getUsername().equals(user.getUsername()) && userRepository.existsByUsername(dto.getUsername())) {
            throw new UsernameAlreadyExistsException();
        }

        /* Construct User Object */
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setName(dto.getName());

        /* Pass to repository and return the result */
        return userRepository.save(user);
    }
}
