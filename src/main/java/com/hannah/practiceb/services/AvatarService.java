package com.hannah.practiceb.services;

import com.hannah.practiceb.models.Avatar;
import com.hannah.practiceb.repositiories.AvatarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvatarService {
    private AvatarRepository avatarRepository;

    /**
     * A constructor to be used to inject dependencies
     *
     * @param avatarRepository
     */
    public void AvatarService(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    /**
     * Returns an avatar object with a given id
     *
     * @param id
     * @return
     */
    public Avatar findById(long id) {
        Optional<Avatar> avatarOpt = avatarRepository.findById(id);
        if (avatarOpt.isPresent()) {
            return avatarOpt.get();
        }
        return null;
    }

    /**
     * Returns an avatar object with a given name
     *
     * @param name
     * @return
     */
    public Avatar findByName(String name) {
        Optional<Avatar> avatarOpt = avatarRepository.findByName(name);
        if (avatarOpt.isPresent()) {
            return avatarOpt.get();
        }
        return null;
    }

    /**
     * Returns a list of all avatars in the database
     *
     * @return
     */
    public List<Avatar> findAllAvatars() {
        return avatarRepository.findAll();
    }
}
