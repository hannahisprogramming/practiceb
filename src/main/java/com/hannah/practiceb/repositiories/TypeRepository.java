package com.hannah.practiceb.repositiories;

import com.hannah.practiceb.models.Type;
import com.hannah.practiceb.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("type")
public interface TypeRepository extends JpaRepository<Type, Long> {
    /**
     * Returns a type object based on a given type name
     *
     * @param type (String)
     * @return a type object
     */
    Optional<Type> findByType(String type);
}
