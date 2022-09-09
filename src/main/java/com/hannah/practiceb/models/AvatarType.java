package com.hannah.practiceb.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class AvatarType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private  String type;
    private int health;
    private String attack;
    private int attackDamage;

    public AvatarType() {
        this.id = 01;
        this.type = "";
        this.health = 0;
        this.attack = "";
        this.attackDamage = 0;
    }

    public AvatarType(String type, int health, String attack, int attackDamage) {
        this.id = 0;
        this.type = type;
        this.health = health;
        this.attack = attack;
        this.attackDamage = attackDamage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getAttack() {
        return attack;
    }

    public void setAttack(String attack) {
        this.attack = attack;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }
}
