package com.hannah.practiceb.services;

import com.hannah.practiceb.models.Potion;
import com.hannah.practiceb.repositiories.PotionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PotionService {
    private PotionRepository potionRepository;

    /**
     * A constructor to be used to inject dependencies
     *
     * @param potionRepository
     */
    public void PotionService(PotionRepository potionRepository) {
        this.potionRepository = potionRepository;
    }

    /**
     * Returns a potion object with a given id
     *
     * @param id
     * @return
     */
    public Potion findById(long id) {
        Optional<Potion> potionOpt = potionRepository.findById(id);
        if (potionOpt.isPresent()) {
            return potionOpt.get();
        }
        return null;
    }

    /**
     * Returns a potion object with a given potion name
     *
     * @param potion
     * @return
     */
    public Potion findByName(String potion) {
        Optional<Potion> potionOpt = potionRepository.findByPotion(potion);
        if (potionOpt.isPresent()) {
            return potionOpt.get();
        }
        return null;
    }

    /**
     * Returns a list of all potions in the database
     *
     * @return
     */
    public List<Potion> findAllPotions() {
        return potionRepository.findAll();
    }
}
