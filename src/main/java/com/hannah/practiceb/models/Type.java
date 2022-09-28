package com.hannah.practiceb.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "types")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "type")
    private String type;
    @Column(name = "description")
    private String description;
    @Column(name = "health")
    private int health;
    @Column(name = "attack")
    private String attack;
    @Column(name = "attackDamage")
    private int attackDamage;
}
