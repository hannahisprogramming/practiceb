package com.hannah.practiceb.repositiories;

import com.hannah.practiceb.models.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    /**
     * Returns an avatar object based on a given name
     *
     * @param name (String)
     * @return an avatar object
     */
    Optional<Avatar> findByName(String name);

    /**
     * Returns an avatar object based on a given name
     *
     * @param type (String)
     * @return an avatar object
     */
    Optional<Avatar> findByType(String type);
}
