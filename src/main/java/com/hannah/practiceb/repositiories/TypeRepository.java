package com.hannah.practiceb.repositiories;

import com.hannah.practiceb.models.Type;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeRepository {
    /**
     * Returns a type object based on a given type name
     *
     * @param type (String)
     * @return a type object
     */
    Optional<Type> findByType(String type);
}
