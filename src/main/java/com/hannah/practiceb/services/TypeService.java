package com.hannah.practiceb.services;

import com.hannah.practiceb.models.Type;
import com.hannah.practiceb.repositiories.TypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeService {
    private TypeRepository typeRepository;
    /**
     * Returns a type object with a given type title
     *
     * @param type
     * @return
     */
    public Type findByType(String type) {
        Optional<Type> typeOpt = typeRepository.findByType(type);
        if (typeOpt.isPresent()) {
            return typeOpt.get();
        }
        return null;
    }

    /**
     * Returns a list of all avatars in the database
     *
     * @return
     */
    public List<Type> findAllTypes() {
        return typeRepository.findAll();
    }
}
