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
    private  String type;
    private int health;
    private String attack;
    private int attackDamage;
}
