package com.hannah.practiceb.repositiories;

import com.hannah.practiceb.models.Potion;
import com.hannah.practiceb.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PotionRepository extends JpaRepository<Potion, Long> {
    /**
     * Returns a potion object based on a given potion name
     *
     * @param potion (String)
     * @return a potion object
     */
    Optional<Potion> findByPotion(String potion);
}
