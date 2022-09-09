package com.hannah.practiceb.repositiories;

import com.hannah.practiceb.models.User;

import java.util.Optional;

public interface PotionRepository {
    /**
     * Returns a potion object based on a given potion name
     *
     * @param potion (String)
     * @return a potion object
     */
    Optional<User> findByPotion(String potion);
}
