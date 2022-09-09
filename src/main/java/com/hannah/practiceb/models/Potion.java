package com.hannah.practiceb.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Potion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String potion;
    private int potionHeal;

    public Potion() {
        this.id = 01;
        this.potion = "";
        this.potionHeal = 0;
    }

    public Potion(String potion, int potionHeal) {
        this.id = 0;
        this.potion = potion;
        this.potionHeal = potionHeal;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPotion() {
        return potion;
    }

    public void setPotion(String potion) {
        this.potion = potion;
    }

    public int getPotionHeal() {
        return potionHeal;
    }

    public void setPotionHeal(int potionHeal) {
        this.potionHeal = potionHeal;
    }
}
